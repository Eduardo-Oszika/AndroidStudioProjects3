package com.oszika.bemcalcula.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.oszika.bemcalcula.R;
import com.oszika.bemcalcula.util.CalculadoraIMC;
import com.oszika.bemcalcula.util.ClassificacaoIMC;

public class CalculoIMCActivity extends AppCompatActivity {
    private CalculadoraIMC calculo;
    private EditText etPeso, etAltura;
    private Button btnCalcularIMC;
    private ClassificacaoIMC classificacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculo_imc_activity);

        inicializarComponentes();

        btnCalcularIMC.setOnClickListener(view -> calcularIMC());
    }

    private void calcularIMC() {
        if (verificarCampos()) {
            double peso = Double.parseDouble(etPeso.getText().toString());
            double altura = Double.parseDouble(etAltura.getText().toString());
            double imc = calculo.calcularIMC(peso, altura);
            classificacao = calculo.classificarIMC(imc);

            Intent intent = new Intent(CalculoIMCActivity.this, ResultadoIMCActivity.class);
            intent.putExtra("imc", imc);
            intent.putExtra("classificacao", classificacao.ordinal());
            startActivity(intent);
        }
    }

    private boolean verificarCampos() {
        String peso = etPeso.getText().toString();
        String altura = etAltura.getText().toString();
        if (peso.isEmpty()) {
            etPeso.setError(getString(R.string.campo_obrigatorio));
            return false;
        }
        if (altura.isEmpty()) {
            etAltura.setError(getString(R.string.campo_obrigatorio));
            return false;
        }
        if (Double.parseDouble(peso) <= 0) {
            etPeso.setError(getString(R.string.peso_invalido));
            return false;
        }
        if (Double.parseDouble(altura) <= 0) {
            etAltura.setError(getString(R.string.altura_invalida));
            return false;
        }
        return true;
    }

    private void inicializarComponentes() {
        etPeso = findViewById(R.id.etPeso);
        etAltura = findViewById(R.id.etAltura);
        btnCalcularIMC = findViewById(R.id.btnCalcularIMC);
        calculo = new CalculadoraIMC();
    }
}