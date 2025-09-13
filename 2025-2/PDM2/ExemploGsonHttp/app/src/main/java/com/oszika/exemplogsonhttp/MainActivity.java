package com.oszika.exemplogsonhttp;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> listaStrings;
    private final String URL = "https://jsonplaceholder.typicode.com/posts";
    private List<Item> dadosBaixados;
    private ExecutorService executorService;
    private Handler mainHandler;
    private EditText editText;
    private TextView txtResultado;
    private Button btnPesquisar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lvDados);
        editText = findViewById(R.id.etNumero);
        txtResultado = findViewById(R.id.txtResultado);
        btnPesquisar = findViewById(R.id.btnPesquisar);
        listaStrings = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaStrings);
        listView.setAdapter(adapter);
        executorService = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());
        obterDados();
    }

    private void obterDados() {
        Toast.makeText(getApplicationContext(), "Download começando...", Toast.LENGTH_SHORT).show();
        executorService.execute(() -> {
            try {
                Conexao conexao = new Conexao();
                InputStream inputStream = conexao.obterRespostaHTTP(URL);
                Conversao conversao = new Conversao();
                String textoJSON = conversao.converter(inputStream);
                Log.i("JSON", "doInBackground: " + textoJSON);
                if (textoJSON != null) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Item>>() {
                    }.getType();
                    dadosBaixados = gson.fromJson(textoJSON, type);
                }
                List<String> finalLista = new ArrayList<>();
                if (dadosBaixados != null) {
                    for (Item item : dadosBaixados) {
                        finalLista.add("UserID: " + item.getUserId()
                                + "\nID: " + item.getId()
                                + "\nTítulo: " + item.getTitle()
                                + "\nBody: " + item.getBody());
                    }
                }
                mainHandler.post(() -> {
                    if (dadosBaixados != null) {
                        listaStrings.clear();
                        listaStrings.addAll(finalLista);
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getApplicationContext(), "Não foi possível obter JSON", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                mainHandler.post(() ->
                        Toast.makeText(getApplicationContext(), "Erro ao baixar dados", Toast.LENGTH_SHORT).show()
                );
            }
        });
    }

    public void setVisivelBuscar(View view) {
        listView.setVisibility(View.GONE);
        editText.setVisibility(View.VISIBLE);
        txtResultado.setVisibility(View.VISIBLE);
        btnPesquisar.setVisibility(View.VISIBLE);
    }

    public void setVisivelListar(View view) {
        editText.setVisibility(View.GONE);
        txtResultado.setVisibility(View.GONE);
        btnPesquisar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }

    public void pesquisar(View view) {
        String numeroStr = editText.getText().toString();
        if (numeroStr.isEmpty()) {
            Toast.makeText(this, "Por favor, insira um número", Toast.LENGTH_SHORT).show();
            return;
        }
        executorService.execute(() -> {
            Conexao conexao = new Conexao();
            InputStream inputStream = conexao.obterRespostaHTTP(URL+"/"+numeroStr);
            Conversao conversao = new Conversao();
            String textoJSON = conversao.converter(inputStream);
            Log.i("JSON", "doInBackground: " + textoJSON);

            if (textoJSON != null) {
                Gson gson = new Gson();
                Item item = gson.fromJson(textoJSON, Item.class);
                String resultado = "UserID: " + item.getUserId()
                        + "\nID: " + item.getId()
                        + "\nTítulo: " + item.getTitle()
                        + "\nBody: " + item.getBody();
                mainHandler.post(() -> txtResultado.setText(resultado));
            } else {
                mainHandler.post(() -> Toast.makeText(this, "Número inválido", Toast.LENGTH_SHORT).show());
            }

        });
    }
}