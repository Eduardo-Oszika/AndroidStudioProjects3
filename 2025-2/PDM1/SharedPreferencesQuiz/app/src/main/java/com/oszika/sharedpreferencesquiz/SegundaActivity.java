package com.oszika.sharedpreferencesquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SegundaActivity extends AppCompatActivity {
    RadioGroup questao1, questao2, questao3;
    Button btnEnviar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_segunda);

        questao1 = findViewById(R.id.q1);
        questao2 = findViewById(R.id.q2);
        questao3 = findViewById(R.id.q3);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(v -> {
            int acertos = 0;

            // Gabarito
            if (questao1.getCheckedRadioButtonId() == R.id.q1resp1) acertos++;
            if (questao2.getCheckedRadioButtonId() == R.id.q2resp3) acertos++;
            if (questao3.getCheckedRadioButtonId() == R.id.q3resp2) acertos++;

            int erros = 3 - acertos;

            // Salvar no SharedPreferences
            SharedPreferences prefs = getSharedPreferences("APP_PREFS", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("acertos", acertos);
            editor.putInt("erros", erros);
            editor.apply();

            // Ir para a próxima tela
            Intent i = new Intent(SegundaActivity.this, ResultadoActivity.class);
            startActivity(i);
            finish();
        });
    }
}