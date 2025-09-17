package com.oszika.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText etNumero1, etNumero2;
    private double num1, num2, resultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etNumero1 = findViewById(R.id.etNumero1);
        etNumero2 = findViewById(R.id.etNumero2);
    }

    public void operacaoSomar(View view) {
        if (verificarCampos()) {
            getNumeros();
            resultado = num1 + num2;
            iniciarActivityResultado();
        }
    }

    public void operacaoSubtrair(View view) {
        if (verificarCampos()) {
            getNumeros();
            resultado = num1 - num2;
            iniciarActivityResultado();
        }
    }

    public void operacaoMultiplicar(View view) {
        if (verificarCampos()) {
            getNumeros();
            resultado = num1 * num2;
            iniciarActivityResultado();
        }
    }

    public void operacaoDividir(View view) {
        if (verificarCampos()) {
            getNumeros();
            if (num2 != 0) {
                resultado = num1 / num2;
            } else {
                resultado = 0;
            }
            iniciarActivityResultado();
        }
    }

    private void getNumeros() {
        num1 = Double.parseDouble(etNumero1.getText().toString());
        num2 = Double.parseDouble(etNumero2.getText().toString());
    }

    private boolean verificarCampos() {
        if (etNumero1.getText().toString().isEmpty() || etNumero2.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor, insira ambos os números.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void iniciarActivityResultado() {
        Intent intent = new Intent(this, ResultadoActivity.class);
        intent.putExtra("resultado", String.valueOf(resultado));
        startActivity(intent);
    }
}