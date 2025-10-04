package com.oszika.bemcalcula.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.oszika.bemcalcula.R;
import com.oszika.bemcalcula.util.Calculadora;
import com.oszika.bemcalcula.util.Converter;

public class CalculoBasicoActivity extends AppCompatActivity {
    private Calculadora calculo;
    private Button[] buttonsTexto;
    private Button[] buttonsOperacao;
    private Button btnAC, btnApagar, btnIgual, btnPonto, btnBIN;
    private TextView txtNum1, txtNum2, txtOperacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculo_basico);

        inicializarComponentes();

        adicionaTexto();
        adicionaOperador();

        btnAC.setOnClickListener(view -> funcaoAC());
        btnApagar.setOnClickListener(view -> apagar());
        btnIgual.setOnClickListener(view -> calcular());
        btnPonto.setOnClickListener(view -> adicionaPonto());
        btnBIN.setOnClickListener(view -> converterBin());
    }

    private void converterBin() {
        String snum2 = txtNum2.getText().toString();
        if (!snum2.equals("0") && !snum2.contains(".")) {
            long num2 = Integer.parseInt(snum2);
            if (num2 > 0) {
                String resultado = Converter.converterParaBinario(num2);
                txtNum1.setText(num2 + " =");
                txtNum2.setText(resultado);
            }
        }
    }

    private void adicionaTexto() {
        for (int i = 0; i < buttonsTexto.length; i++) {
            int num = i;
            buttonsTexto[i].setOnClickListener(view -> {
                String num2 = txtNum2.getText().toString();
                if (num2.equals("0"))
                    txtNum2.setText(String.valueOf(num));
                else
                    txtNum2.setText(num2 + num);
            });
        }
    }

    private void adicionaPonto() {
        String num2 = txtNum2.getText().toString();
        if (!num2.contains(".")) {
            txtNum2.setText(num2 + ".");
        }
    }

    private void adicionaOperador() {
        for (int i = 0; i < buttonsOperacao.length; i++) {
            int operador = i;
            buttonsOperacao[i].setOnClickListener(view -> {
                String num2 = txtNum2.getText().toString();
                if (num2.equals("0") && operador == 3) {
                    Snackbar.make(buttonsOperacao[operador], "Não é possível dividir por zero", Snackbar.LENGTH_LONG).show();
                } else {
                    txtNum1.setText(txtNum2.getText().toString());
                    txtOperacao.setText(buttonsOperacao[operador].getText().toString());
                    txtNum2.setText("0");
                }
            });
        }
    }

    private void calcular() {
        String num1 = txtNum1.getText().toString();
        String num2 = txtNum2.getText().toString();
        String operacao = txtOperacao.getText().toString();
        if (!num1.isEmpty() || !num2.isEmpty() || !operacao.isEmpty()) {
            double resultado = 0;
            try {
                double numero1 = Double.parseDouble(num1);
                double numero2 = Double.parseDouble(num2);

                switch (operacao) {
                    case "+":
                        resultado = calculo.somar(numero1, numero2);
                        break;
                    case "-":
                        resultado = calculo.subtrair(numero1, numero2);
                        break;
                    case "X":
                        resultado = calculo.multiplicar(numero1, numero2);
                        break;
                    case "/":
                        resultado = calculo.dividir(numero1, numero2);
                        break;
                }

                txtNum1.setText(num1 + " " + operacao + " " + num2 + " =");
                txtNum2.setText(String.valueOf(resultado));
                txtOperacao.setText("");

            } catch (IllegalArgumentException e) {
                int erro = Integer.parseInt(e.getMessage());
                Snackbar.make(btnIgual, getString(erro), Snackbar.LENGTH_LONG).show();
            }
        }
    }

    private void apagar() {
        String num2 = txtNum2.getText().toString();
        String num1 = txtNum1.getText().toString();
        if (!num2.equals("0") && !num2.isEmpty()) {
            txtNum2.setText(num2.substring(0, num2.length() - 1));
            if (txtNum2.getText().toString().isEmpty()) {
                txtNum2.setText("0");
            }
        } else if (num1.isEmpty()) {
            txtNum2.setText("0");
        } else if (num1.contains("=")) {
            txtNum1.setText("");
            txtNum2.setText("0");
            txtOperacao.setText("");
        } else {
            txtNum2.setText(num1);
            txtNum1.setText("");
            txtOperacao.setText("");
        }
    }

    private void funcaoAC() {
        txtNum1.setText("");
        txtNum2.setText("0");
        txtOperacao.setText("");
    }

    private void inicializarComponentes() {
        btnAC = findViewById(R.id.btAC);
        btnApagar = findViewById(R.id.btApagar);
        btnIgual = findViewById(R.id.btIgual);
        btnBIN = findViewById(R.id.btBin);

        txtNum1 = findViewById(R.id.txtNum1);
        txtNum2 = findViewById(R.id.txtNum2);
        txtOperacao = findViewById(R.id.txtOperacao);
        calculo = new Calculadora();

        buttonsTexto = new Button[10];
        buttonsTexto[0] = findViewById(R.id.bt0);
        buttonsTexto[1] = findViewById(R.id.bt1);
        buttonsTexto[2] = findViewById(R.id.bt2);
        buttonsTexto[3] = findViewById(R.id.bt3);
        buttonsTexto[4] = findViewById(R.id.bt4);
        buttonsTexto[5] = findViewById(R.id.bt5);
        buttonsTexto[6] = findViewById(R.id.bt6);
        buttonsTexto[7] = findViewById(R.id.bt7);
        buttonsTexto[8] = findViewById(R.id.bt8);
        buttonsTexto[9] = findViewById(R.id.bt9);

        buttonsOperacao = new Button[4];
        buttonsOperacao[0] = findViewById(R.id.btSomar);
        buttonsOperacao[1] = findViewById(R.id.btSubtrair);
        buttonsOperacao[2] = findViewById(R.id.btMultiplicar);
        buttonsOperacao[3] = findViewById(R.id.btDividir);
        btnPonto = findViewById(R.id.btPonto);
    }


}