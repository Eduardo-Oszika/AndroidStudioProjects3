package com.oszika.provapdm1v2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.oszika.provapdm1v2.R;
import com.oszika.provapdm1v2.entity.Jogada;
import com.oszika.provapdm1v2.entity.Pergunta;
import com.oszika.provapdm1v2.service.ServiceJogadas;

import java.util.ArrayList;
import java.util.List;

public class PontuacaoTotal extends AppCompatActivity {

    private ServiceJogadas serviceJogadas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pontuacao_total);
        Toolbar toolbar = findViewById(R.id.meu_toolbar);
        setSupportActionBar(toolbar);

        serviceJogadas = new ServiceJogadas(getApplicationContext());
        TextView tvPontuacaoTotal = findViewById(R.id.txt_pontTotal);

        Jogada jogada = serviceJogadas.getUltimaJogada();
        double pontuacaoTotal = jogada.pontuacao;
        String pontuacaoStr = String.format("%.2f", pontuacaoTotal);
        tvPontuacaoTotal.setText("Pontuação Total: " + pontuacaoStr);


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