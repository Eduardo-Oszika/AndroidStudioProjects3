package com.oszika.provapdm1v2.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oszika.provapdm1v2.R;
import com.oszika.provapdm1v2.adapter.MeuAdapter;
import com.oszika.provapdm1v2.entity.Jogada;
import com.oszika.provapdm1v2.entity.Pergunta;
import com.oszika.provapdm1v2.service.ServiceJogadas;

import java.util.ArrayList;
import java.util.List;

public class QuestaoDetalhe extends AppCompatActivity {
    private MeuAdapter adapter;
    private List<Pergunta> lista;
    private Jogada jogada;
    private ServiceJogadas serviceJogadas;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_questao_detalhe);
        Toolbar toolbar = findViewById(R.id.meu_toolbar);
        setSupportActionBar(toolbar);
        serviceJogadas = new ServiceJogadas(getApplicationContext());

        recyclerView = findViewById(R.id.lv_jogadas);
        lista = new ArrayList<>();
        jogada = new Jogada();
        jogada = serviceJogadas.getUltimaJogada();
        lista = serviceJogadas.obterPerguntas(jogada);
        adapter = new MeuAdapter(lista, jogada);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent it;
        if (R.id.pontotal == item.getItemId()) {
            it = new Intent(this, PontuacaoTotal.class);
            startActivity(it);
            return true;
        }
        if (R.id.detalhe == item.getItemId()) {
            it = new Intent(this, QuestaoDetalhe.class);
            startActivity(it);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}