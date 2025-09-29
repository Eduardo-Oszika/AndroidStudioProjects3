package com.oszika.bemcalcula.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.oszika.bemcalcula.R;
import com.oszika.bemcalcula.util.Calculadora;

public class CalculoBasicoActivity extends AppCompatActivity {
    private Calculadora calculo;
    private Button btnAC, btnApagar, btnIgual, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPonto;
    private TextView txtNum1, txtNum2, txtOperacao;
    private RadioGroup rgOperacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculo_basico);

        inicializarComponentes();

        rgOperacoes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1) {
                    String textoNum2 = txtNum2.getText().toString();
                    if (!textoNum2.isEmpty()) {
                        RadioButton rb = findViewById(checkedId);
                        String operacao = rb.getText().toString();
                        if (txtNum1.getText().toString().isEmpty())
                            txtNum1.setText(textoNum2);
                        txtOperacao.setText(operacao);
                        txtNum2.setText("0");
                    }
                }
            }
        });
        adicionaTexto();

        btnAC.setOnClickListener(view -> funcaoAC());
        btnApagar.setOnClickListener(view -> apagar());
        btnIgual.setOnClickListener(view -> calcular());
    }

    private void adicionaTexto() {
        btn0.setOnClickListener(view -> {
            String num2 = txtNum2.getText().toString();
            if (!num2.equals("0"))
                txtNum2.setText(txtNum2.getText().toString() + "0");
        });
        btn1.setOnClickListener(view -> {
            String num2 = txtNum2.getText().toString();
            if (num2.equals("0"))
                txtNum2.setText("1");
            else
                txtNum2.setText(num2 + "1");
        });
        btn2.setOnClickListener(view -> {
            String num2 = txtNum2.getText().toString();
            if (num2.equals("0"))
                txtNum2.setText("2");
            else
                txtNum2.setText(num2 + "2");
        });
        btn3.setOnClickListener(view -> {
            String num2 = txtNum2.getText().toString();
            if (num2.equals("0"))
                txtNum2.setText("3");
            else
                txtNum2.setText(num2 + "3");
        });
        btn4.setOnClickListener(view -> {
            String num2 = txtNum2.getText().toString();
            if (num2.equals("0"))
                txtNum2.setText("4");
            else
                txtNum2.setText(num2 + "4");
        });
        btn5.setOnClickListener(view -> {
            String num2 = txtNum2.getText().toString();
            if (num2.equals("0"))
                txtNum2.setText("5");
            else
                txtNum2.setText(num2 + "5");
        });
        btn6.setOnClickListener(view -> {
            String num2 = txtNum2.getText().toString();
            if (num2.equals("0"))
                txtNum2.setText("6");
            else
                txtNum2.setText(num2 + "6");
        });
        btn7.setOnClickListener(view -> {
            String num2 = txtNum2.getText().toString();
            if (num2.equals("0"))
                txtNum2.setText("7");
            else
                txtNum2.setText(num2 + "7");
        });
        btn8.setOnClickListener(view -> {
            String num2 = txtNum2.getText().toString();
            if (num2.equals("0"))
                txtNum2.setText("8");
            else
                txtNum2.setText(num2 + "8");
        });
        btn9.setOnClickListener(view -> {
            String num2 = txtNum2.getText().toString();
            if (num2.equals("0"))
                txtNum2.setText("9");
            else
                txtNum2.setText(num2 + "9");
        });

        btnPonto.setOnClickListener(view -> {
            String num2 = txtNum2.getText().toString();
            if (!num2.contains(".")) {
                txtNum2.setText(num2 + ".");
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

                txtNum1.setText(num1+" "+operacao+" "+num2+" =");
                txtNum2.setText(String.valueOf(resultado));
                txtOperacao.setText("");
                rgOperacoes.clearCheck();

            } catch (IllegalArgumentException e) {
                Snackbar.make(btnIgual, e.getMessage(), Snackbar.LENGTH_LONG).show();
            }
        }
    }

    private void apagar() {
        String num2 = txtNum2.getText().toString();
        if (!num2.equals("0")) {
            txtNum2.setText(num2.substring(0, num2.length() - 1));
        } else if (txtNum1.getText().toString().isEmpty()) {
            txtNum2.setText("0");
        } else {
            rgOperacoes.clearCheck();
            txtNum2.setText(txtNum1.getText().toString());
            txtNum1.setText("");
            txtOperacao.setText("");
        }
    }

    private void funcaoAC() {
        txtNum1.setText("");
        txtNum2.setText("0");
        txtOperacao.setText("");
        rgOperacoes.clearCheck();
    }

    private void inicializarComponentes() {
        btnAC = findViewById(R.id.btAC);
        btnApagar = findViewById(R.id.btApagar);
        btnIgual = findViewById(R.id.btIgual);
        txtNum1 = findViewById(R.id.txtNum1);
        txtNum2 = findViewById(R.id.txtNum2);
        txtOperacao = findViewById(R.id.txtOperacao);
        calculo = new Calculadora();
        rgOperacoes = findViewById(R.id.rgOperacoes);

        btn0 = findViewById(R.id.bt0);
        btn1 = findViewById(R.id.bt1);
        btn2 = findViewById(R.id.bt2);
        btn3 = findViewById(R.id.bt3);
        btn4 = findViewById(R.id.bt4);
        btn5 = findViewById(R.id.bt5);
        btn6 = findViewById(R.id.bt6);
        btn7 = findViewById(R.id.bt7);
        btn8 = findViewById(R.id.bt8);
        btn9 = findViewById(R.id.bt9);
        btnPonto = findViewById(R.id.btPonto);
    }


}