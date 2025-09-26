package com.oszika.exemplolanchonete;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class BoloActivity extends AppCompatActivity {
    private EditText etBolo;
    private Button btnBolo;
    private Calculadora calculadora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bolo);

        etBolo = findViewById(R.id.etQtdBolo);
        btnBolo = findViewById(R.id.bBolo);
        calculadora = new Calculadora();

        btnBolo.setOnClickListener(v -> {
            obterDadosBolo();
        });
    }

    private void obterDadosBolo() {
        int qtdBolo = Integer.parseInt(etBolo.getText().toString());
        double total = calculadora.calcularTotalBolo(qtdBolo);
        Snackbar.make(btnBolo, "Total do Bolo: R$ " + String.format("%.2f", total), Snackbar.LENGTH_LONG).show();
        total += getIntent().getDoubleExtra("total",0);
        Intent intent = new Intent(BoloActivity.this, MainActivity.class);
        intent.putExtra("total", total);
        startActivity(intent);

    }
}