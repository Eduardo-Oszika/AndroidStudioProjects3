package com.oszika.bemcalcula.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.oszika.bemcalcula.R;
import com.oszika.bemcalcula.util.ClassificacaoIMC;

public class ResultadoIMCActivity extends AppCompatActivity {
    TextView tvResultadoIMC, tvClassificacaoIMC;
    ImageView ivResultadoIMC;
    double imc;
    ClassificacaoIMC classificacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultado_imc_activity);

        inicializarComponentes();
        setComponentes();

    }

    private void setComponentes() {
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

        imc = getIntent().getDoubleExtra("imc",0);
        int ordinal = getIntent().getIntExtra("classificacao",0);
        classificacao = ClassificacaoIMC.values()[ordinal];

    }
}