package com.oszika.exemplospinnerprogressbar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText edtNome;
    private RadioButton rdbI, rdbR;
    private Spinner spinner;
    private ProgressBar progressBar;
    private TextView textViewResultado;
    private Estudante estudante;
    private int progresso = 0;
    private int idadeSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtNome = findViewById(R.id.editTextNome);
        rdbI = findViewById(R.id.radioButtonI);
        rdbR = findViewById(R.id.radioButtonR);
        spinner = findViewById(R.id.spinner);
        progressBar = findViewById(R.id.progressBar);
        textViewResultado = findViewById(R.id.textViewResultado);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.idades_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);

    }

    private void executarProgressBar() {
        progressBar.setProgress(progresso);
        progressBar.setVisibility(View.VISIBLE);

        Handler handler = new Handler(Looper.getMainLooper());

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progresso < 100) {
                    progresso += 10;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progresso);
                            if (progresso == 100) {
                                textViewResultado.setText(estudante.toString());
                            }
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void clicar(View view) {
        String nome = edtNome.getText().toString();
        if (rdbI == null && rdbR == null) {
            Toast.makeText(this, "Selecione a situação", Toast.LENGTH_SHORT).show();
            return;
        }
        if (nome.isEmpty()) {
            edtNome.setError("Informe o nome");
            edtNome.requestFocus();
            return;
        }
        String situacao = "";
        if (rdbI.isChecked())
            situacao = rdbI.getText().toString();
        if (rdbR.isChecked())
            situacao = rdbR.getText().toString();
        idadeSelecionada = Integer.parseInt(spinner.getSelectedItem().toString());

        estudante = new Estudante(nome,situacao,idadeSelecionada);

        executarProgressBar();
    }
}