package com.oszika.gsonrelatorioturma.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oszika.gsonrelatorioturma.R;
import com.oszika.gsonrelatorioturma.entity.Aluno;
import com.oszika.gsonrelatorioturma.entity.Turma;
import com.oszika.gsonrelatorioturma.util.Conexao;
import com.oszika.gsonrelatorioturma.util.Conversao;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private TextView textViewMediaIdade;
    private ArrayAdapter<Aluno> adapterAluno;
    private Turma turma;

    private final String URL = "https://my-json-server.typicode.com/Eduardo-Oszika/AndroidStudioProjects3/alunos";
    private ExecutorService executorService;
    private Handler mainHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lvAprovados);
        textViewMediaIdade = findViewById(R.id.tvMediaIdades);

        turma = new Turma();

        turma.setAlunos(new ArrayList<>());
        adapterAluno = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, turma.getAprovados());

        listView.setAdapter(adapterAluno);
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
                    Type type = new TypeToken<List<Aluno>>() {
                    }.getType();
                    turma.setAlunos(gson.fromJson(textoJSON, type));
                }
                mainHandler.post(() -> {
                    if (turma.getAlunos().isEmpty() == false) {
                        System.out.println(turma.getAlunos());
                        textViewMediaIdade.setText(String.format("Média das idades: %.2f", turma.mediaIdade()));
                        adapterAluno.clear();
                        adapterAluno.addAll(turma.getAprovados());
                        adapterAluno.notifyDataSetChanged();
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