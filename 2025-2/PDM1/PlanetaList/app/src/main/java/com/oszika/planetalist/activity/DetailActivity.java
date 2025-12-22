package com.oszika.planetalist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.oszika.planetalist.R;

public class DetailActivity extends AppCompatActivity {

    TextView tvNome, tvTemperatura, tvDistancia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        Intent it = getIntent();

        tvNome = findViewById(R.id.tv_nome);
        tvTemperatura = findViewById(R.id.tv_temperatura);
        tvDistancia = findViewById(R.id.tv_distancia);

        tvNome.setText(it.getStringExtra("nome"));
        tvTemperatura.setText(it.getStringExtra("temperatura"));
        tvDistancia.setText(it.getStringExtra("distancia"));
    }
}