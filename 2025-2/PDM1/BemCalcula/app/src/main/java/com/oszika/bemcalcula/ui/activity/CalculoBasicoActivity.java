package com.oszika.bemcalcula.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.oszika.bemcalcula.R;
import com.oszika.bemcalcula.util.Calculadora;

public class CalculoBasicoActivity extends AppCompatActivity {
    private Calculadora calculo;
    private Button[] buttonsTexto;
    private Button btnAC, btnApagar, btnIgual, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPonto, btnSomar, btnSubtrair, btnMultiplicar, btnDividir;
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
        btnSomar.setOnClickListener(view -> {
            txtNum1.setText(txtNum2.getText().toString());
            txtOperacao.setText("+");
            txtNum2.setText("0");
        });

        btnSubtrair.setOnClickListener(view -> {
            txtNum1.setText(txtNum2.getText().toString());
            txtOperacao.setText("-");
            txtNum2.setText("0");
        });

        btnMultiplicar.setOnClickListener(view -> {
            txtNum1.setText(txtNum2.getText().toString());
            txtOperacao.setText("X");
            txtNum2.setText("0");
        });
        btnDividir.setOnClickListener(view -> {
            String num2 = txtNum2.getText().toString();
            if (num2.equals("0")) {
                Snackbar.make(btnDividir, "Não é possível dividir por zero", Snackbar.LENGTH_LONG).show();
            } else {
                txtNum1.setText(txtNum2.getText().toString());
                txtOperacao.setText("/");
                txtNum2.setText("0");
            }
        });
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
                Snackbar.make(btnIgual, e.getMessage(), Snackbar.LENGTH_LONG).show();
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

        btnPonto = findViewById(R.id.btPonto);
        btnSomar = findViewById(R.id.btSomar);
        btnSubtrair = findViewById(R.id.btSubtrair);
        btnMultiplicar = findViewById(R.id.btMultiplicar);
        btnDividir = findViewById(R.id.btDividir);
    }


}