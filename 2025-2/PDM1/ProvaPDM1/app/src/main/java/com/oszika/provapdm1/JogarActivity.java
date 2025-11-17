package com.oszika.provapdm1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.oszika.provapdm1.dialog.MyDialogFragment;
import com.oszika.provapdm1.entity.Elementos;
import com.oszika.provapdm1.entity.Rodada;
import com.oszika.provapdm1.entity.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class JogarActivity extends AppCompatActivity {

    private EditText etNumero, etAdivinhar;
    private ProgressBar progressBar;
    private TextView tvProgress, tvAdivinhar, tvResultado;
    private List<Integer> posicaoNumerosAtomicos;
    private Button btnAdivinhar;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jogar);
        etNumero = findViewById(R.id.et_numero_digitado);
        progressBar = findViewById(R.id.progres);
        tvProgress = findViewById(R.id.tv_mostrar_numeros);
        etAdivinhar = findViewById(R.id.et_adivinha_numero);
        tvAdivinhar = findViewById(R.id.tv_adivinhar_numero);
        tvResultado = findViewById(R.id.tv_resultado);
        btnAdivinhar = findViewById(R.id.buttonAdivinhar);

        Toolbar toolbar = findViewById(R.id.meu_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void clicou(View view) {
        int numeroDigitado = Integer.parseInt(etNumero.getText().toString());
        posicaoNumerosAtomicos = new ArrayList<>();
        for (Elementos elemento : Elementos.values()) {
            posicaoNumerosAtomicos.add(elemento.getNumeroAtomico());
        }
        Collections.shuffle(posicaoNumerosAtomicos, new Random());
        posicaoNumerosAtomicos = posicaoNumerosAtomicos.subList(0, numeroDigitado);

        executarProgressBar(posicaoNumerosAtomicos);


        etNumero.setVisibility(View.INVISIBLE);
        view.setVisibility(View.INVISIBLE);
//        Intent it = new Intent(this, Jogar2Activity.class);
//        it.putExtra("listaNumerosAtomicos", new ArrayList<>(posicaoNumerosAtomicos));
    }

    private void executarProgressBar(List<Integer> posicaoNumeros) {
        progressBar.setProgress(0);
        int x = posicaoNumeros.size();
        progressBar.setMax(posicaoNumeros.size());
        progressBar.setVisibility(View.VISIBLE);
        tvProgress.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= posicaoNumerosAtomicos.size(); i += 1) {
                    int progressoAtual = i;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                                progressBar.setProgress(progressoAtual);
                                if (progressoAtual < posicaoNumerosAtomicos.size()) {
                                    tvProgress.setText("Numero: " + (posicaoNumerosAtomicos.get(progressoAtual)));
                                }
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.INVISIBLE);
                        tvProgress.setVisibility(View.INVISIBLE);
                        etAdivinhar.setVisibility(View.VISIBLE);
                        tvAdivinhar.setVisibility(View.VISIBLE);
                        btnAdivinhar.setVisibility(View.VISIBLE);
                    }
                });

            }
        }).start();

    }

    public void adivinhar(View view) {
        tvAdivinhar.setVisibility(View.INVISIBLE);
        etAdivinhar.setVisibility(View.INVISIBLE);
        btnAdivinhar.setVisibility(View.INVISIBLE);
        int num = Integer.parseInt(etAdivinhar.getText().toString());
        int soma = 0;
        Rodada rodada;
        for (int n : posicaoNumerosAtomicos) {
            soma += n;
        }
        if (num == soma) {
            tvResultado.setText("Você acertou! A soma é " + soma);
            rodada = new Rodada(true);
        } else {
            tvResultado.setText("Você errou! A soma correta é " + soma + "; e você chutou " + num);
            rodada = new Rodada(false);
        }
        usuario = getIntent().getSerializableExtra("user", Usuario.class);
        if (usuario == null){
            usuario = new Usuario();}
        usuario.addRodad(rodada);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        MyDialogFragment dialog = new MyDialogFragment(usuario);
        if (R.id.jogar_novamente == item.getItemId()){
            dialog.show(getSupportFragmentManager(), "customDialog");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}