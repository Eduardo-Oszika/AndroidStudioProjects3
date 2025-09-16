package com.oszika.exemplogsonserver.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oszika.exemplogsonserver.R;
import com.oszika.exemplogsonserver.entity.Adicional;
import com.oszika.exemplogsonserver.entity.Agenda;
import com.oszika.exemplogsonserver.entity.Contato;
import com.oszika.exemplogsonserver.util.Conexao;
import com.oszika.exemplogsonserver.util.Conversao;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private ListView listView, listView2;
    private ArrayAdapter<Adicional> adapterAdicional;
    private ArrayAdapter<Agenda> adapterAgenda;
    private ArrayList<Agenda> listaAgenda;
    private ArrayList<Adicional> listaAdicional;
    private final String URL = "https://my-json-server.typicode.com/Eduardo-Oszika/AndroidStudioProjects3/db";
    private Contato dadosBaixados;
    private ExecutorService executorService;
    private Handler mainHandler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lvDados);
        listView2 = findViewById(R.id.lvDados2);
        listaAgenda = new ArrayList<>();
        listaAdicional = new ArrayList<>();
        adapterAgenda = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaAgenda);
        adapterAdicional = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaAdicional);
        listView.setAdapter(adapterAgenda);
        listView2.setAdapter(adapterAdicional);

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
                    Type type = new TypeToken<Contato>() {
                    }.getType();
                    dadosBaixados = gson.fromJson(textoJSON, type);
                }

                ArrayList<Agenda> agenda;
                ArrayList<Adicional> adicional;
                if (dadosBaixados != null) {

                    agenda = (ArrayList<Agenda>) dadosBaixados.getAgenda();
                    adicional = (ArrayList<Adicional>) dadosBaixados.getAdicionais();
                    Log.i("JSON", "obterDados: " + dadosBaixados);
                } else {
                    agenda = null;
                    adicional = null;
                }
                mainHandler.post(() -> {
                    if (dadosBaixados != null) {
                        listaAgenda.clear();
                        listaAgenda.addAll(agenda);
                        listaAdicional.clear();
                        listaAdicional.addAll(adicional);
                        adapterAdicional.notifyDataSetChanged();
                        adapterAgenda.notifyDataSetChanged();
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
}