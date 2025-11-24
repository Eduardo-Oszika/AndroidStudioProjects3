package com.oszika.exemplofragment03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private MeuAdapter adapter;
    private List<Estrela> itens;
    private EstrelaRepositorio repositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        repositorio = new EstrelaRepositorio(this);
        itens = repositorio.obterEstrelas();
        adapter = new MeuAdapter(this, itens);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Estrela estrela = (Estrela) adapterView.getItemAtPosition(i);
        Intent intent = new Intent(this, DetalheActivity.class);
        intent.putExtra("estrela_nome", estrela.getNome());
        intent.putExtra("estrela_descricao", estrela.getDescricao());
        startActivity(intent);
    }
}