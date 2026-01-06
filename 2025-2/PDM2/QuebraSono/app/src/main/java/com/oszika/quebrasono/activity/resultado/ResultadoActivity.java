package com.oszika.quebrasono.activity.resultado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.oszika.quebrasono.R;
import com.oszika.quebrasono.entity.Jogo;
import com.oszika.quebrasono.util.service.MusicService;

import java.util.ArrayList;
import java.util.List;

public class ResultadoActivity extends AppCompatActivity {
    private ResultadoViewModel resultadoViewModel;
    private RadioGroup radioGroup;
    private ArrayList<RadioButton> radioButtons;
    private ArrayList<TextView> textViews;
    private ArrayList<TextView> textViewsSorteados;
    private LinearLayout layoutResultado, layoutAlternativa;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultado);

        intent = new Intent(this, MusicService.class);
        //startService(intent);
        startForegroundService(intent);

        radioGroup = findViewById(R.id.radioGroupOptions);
        layoutAlternativa = findViewById(R.id.selecionar_layout);
        layoutResultado = findViewById(R.id.resultado_layout);

        textViews = new ArrayList<>();
        textViewsSorteados = new ArrayList<>();


        referenciarTextview();
        referenciarButtons();

        resultadoViewModel = new ViewModelProvider(this).get(ResultadoViewModel.class);

        resultadoViewModel.getRadiosLive().observe(this, new Observer<ArrayList<RadioButton>>() {
            @Override
            public void onChanged(ArrayList<RadioButton> radio) {
                radioButtons = radio;
            }
        });



        resultadoViewModel.getJogoLive().observe(this, jogo ->{
            resultadoViewModel.setTextoResultado(radioButtons);
        });


    }

    private void referenciarTextview() {
        textViews.add(findViewById(R.id.numero1));
        textViews.add(findViewById(R.id.numero2));
        textViews.add(findViewById(R.id.numero3));
        textViews.add(findViewById(R.id.numero4));
        textViews.add(findViewById(R.id.numero5));
        textViews.add(findViewById(R.id.numero6));

        textViewsSorteados.add(findViewById(R.id.sorteado_numero1));
        textViewsSorteados.add(findViewById(R.id.sorteado_numero2));
        textViewsSorteados.add(findViewById(R.id.sorteado_numero3));
        textViewsSorteados.add(findViewById(R.id.sorteado_numero4));
        textViewsSorteados.add(findViewById(R.id.sorteado_numero5));
        textViewsSorteados.add(findViewById(R.id.sorteado_numero6));
    }



    private void referenciarButtons() {
        radioButtons = new ArrayList<>();
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            if (radioGroup.getChildAt(i) instanceof RadioButton) {
                radioButtons.add((RadioButton) radioGroup.getChildAt(i));
            }
        }
    }

    public void mostrarResultado(View view) {
        resultadoViewModel.setTextViewsLive(textViews);
        resultadoViewModel.setTextViewsSorteadosLive(textViewsSorteados);
        resultadoViewModel.setRadiosLive(radioButtons);
        List<Integer> acertos = resultadoViewModel.verificarResposta();

        int acertosint =0;
        for (int i = 0; i < textViews.size(); i++) {
            if (acertos.get(i) == 1) {
                textViews.get(i).setTextColor(getResources().getColor(R.color.green));
                acertosint ++;
            }
        }

        Snackbar.make(textViews.get(0), "Você acertou "+ acertosint + " números!", Snackbar.LENGTH_LONG).show();
        layoutAlternativa.setVisibility(View.GONE);
        layoutResultado.setVisibility(View.VISIBLE);
        stopService(intent);
    }
}