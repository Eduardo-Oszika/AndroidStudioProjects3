package com.oszika.bemcalcula.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.oszika.bemcalcula.R;
import com.oszika.bemcalcula.entity.Usuario;
import com.oszika.bemcalcula.util.CalculadoraIMC;
import com.oszika.bemcalcula.util.ClassificacaoIMC;

public class CalculoIMCActivity extends AppCompatActivity {
    private EditText etnNome, etPeso, etAltura;
    private Button btnCalcularIMC;


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
            String nome = etnNome.getText().toString();
            double peso = Double.parseDouble(etPeso.getText().toString());
            double altura = Double.parseDouble(etAltura.getText().toString());
            Usuario user = new Usuario(nome, peso, altura);


            Intent intent = new Intent(CalculoIMCActivity.this, ResultadoIMCActivity.class);

            intent.putExtra("user", user);
            startActivity(intent);
        }
    }

    private boolean verificarCampos() {
        String nome = etnNome.getText().toString();
        String peso = etPeso.getText().toString();
        String altura = etAltura.getText().toString();
        if (nome.isEmpty()) {
            etnNome.setError(getString(R.string.campo_obrigatorio));
            return false;
        }
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
        etnNome = findViewById(R.id.etNome);
        etPeso = findViewById(R.id.etPeso);
        etAltura = findViewById(R.id.etAltura);
        btnCalcularIMC = findViewById(R.id.btnCalcularIMC);
    }
}