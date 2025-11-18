package com.oszika.sharedpreferencesquiz;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultadoActivity extends AppCompatActivity {

    TextView tvAcertos, tvErros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        tvAcertos = findViewById(R.id.txtAcertos);
        tvErros = findViewById(R.id.txtErros);

        SharedPreferences prefs = getSharedPreferences("APP_PREFS", MODE_PRIVATE);

        int acertos = prefs.getInt("acertos", 0);
        int erros = prefs.getInt("erros", 0);

        tvAcertos.setText("Acertos: " + acertos);
        tvErros.setText("Erros: " + erros);
    }
}