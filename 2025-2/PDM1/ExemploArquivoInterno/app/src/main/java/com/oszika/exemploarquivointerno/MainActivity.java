package com.oszika.exemploarquivointerno;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button buttonSalvar, buttonExcluir;
    private ManipularArquivo arquivo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        editText = findViewById(R.id.editTextTexto);
        buttonSalvar = findViewById(R.id.buttonSalvar);
        buttonExcluir = findViewById(R.id.buttonExcluir);
        arquivo = new ManipularArquivo(this);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("dados")) {
            String dados = intent.getStringExtra("dados");
            editText.setText(dados);
        }

        buttonSalvar.setOnClickListener(view -> salvar());
        buttonExcluir.setOnClickListener(view -> excluir());

    }

    private void salvar() {
        String conteudo = editText.getText().toString();
        arquivo.salvarArquivo("arquivo_interno.txt", conteudo, false);
        Toast.makeText(this, "texto salvo com sucesso", Toast.LENGTH_SHORT).show();
        editText.setText("");
        Intent intent = new Intent(this, SegundaActivity.class);
        startActivity(intent);
    }

    private void excluir() {
        arquivo.deletarArquivo("arquivo_interno.txt");
        Toast.makeText(this, "arquivo excluído com sucesso", Toast.LENGTH_SHORT).show();
    }
}