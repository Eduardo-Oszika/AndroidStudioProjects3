package com.oszika.bemcalcula.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.oszika.bemcalcula.R;
import com.oszika.bemcalcula.entity.Usuario;
import com.oszika.bemcalcula.util.CalculadoraIMC;
import com.oszika.bemcalcula.util.ClassificacaoIMC;

public class ResultadoIMCActivity extends AppCompatActivity {
    private TextView tvNome, tvResultadoIMC, tvClassificacaoIMC;
    private ImageView ivResultadoIMC;
    private double imc;
    private CalculadoraIMC calculadora;
    private ClassificacaoIMC classificacao;
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultado_imc_activity);

        inicializarComponentes();
        setComponentes();

    }

    private void setComponentes() {
        tvNome.setText(nome);

        StringBuilder sbIMC = new StringBuilder();
        sbIMC.append(tvResultadoIMC.getText());
        sbIMC.append(" ");
        sbIMC.append(String.format("%.2f", imc));
        tvResultadoIMC.setText(sbIMC.toString());

        StringBuilder sbClassificacao = new StringBuilder();
        sbClassificacao.append(tvClassificacaoIMC.getText());
        sbClassificacao.append(" ");
        sbClassificacao.append(getString(classificacao.getStringResId()));
        tvClassificacaoIMC.setText(sbClassificacao.toString());

        ivResultadoIMC.setImageResource(classificacao.getDrawableResId());
    }


    private void inicializarComponentes() {
        tvResultadoIMC = findViewById(R.id.tvIMCResultado);
        tvClassificacaoIMC = findViewById(R.id.tvIMCClassificacao);
        ivResultadoIMC = findViewById(R.id.ivIMCResultado);
        tvNome = findViewById(R.id.tvNome);

        Usuario user = (Usuario) getIntent().getSerializableExtra("user");
        nome = user.getNome();
        calculadora = new CalculadoraIMC();
        imc = calculadora.calcularIMC(user.getPeso(), user.getAltura());
        int ordinal = calculadora.classificarIMC(imc).ordinal();
        classificacao = ClassificacaoIMC.values()[ordinal];

    }
}