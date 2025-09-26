package com.oszika.exemplolanchonete;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class CafeActivity extends AppCompatActivity {
    private EditText etCafe;
    private Button btnCafe;
    private Calculadora calculadora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cafe);

        etCafe = findViewById(R.id.etQtdCafe);
        btnCafe = findViewById(R.id.bCafe);
        calculadora = new Calculadora();

        btnCafe.setOnClickListener(v -> {
            obterDadosCafe();
        });
    }

    private void obterDadosCafe() {
        int qtdCafe = Integer.parseInt(etCafe.getText().toString());
        double total = calculadora.calcularTotalCafe(qtdCafe);
        Snackbar.make(btnCafe, "Total do Cafe: R$ " + String.format("%.2f", total), Snackbar.LENGTH_LONG).show();
        total += getIntent().getDoubleExtra("total",0);
        Intent intent = new Intent(CafeActivity.this, MainActivity.class);
        intent.putExtra("total", total);
        startActivity(intent);

    }
}