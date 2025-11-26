package com.oszika.exemploarquivoexterno;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button buttonSalvar, buttonExcluir;
    private  ManipulaArquivo arquivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextTexto);
        buttonSalvar = findViewById(R.id.buttonSalvar);
        buttonExcluir = findViewById(R.id.buttonExcluir);
        arquivo = new ManipulaArquivo(this);

        Intent intent = getIntent();
        if(intent !=null){
            String texto = intent.getStringExtra("dados");
            editText.setText(texto);
        }//


        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluir();
            }
        });
    }//

    private void salvar() {
        String conteudo = editText.getText().toString();
        try {
            arquivo.salvarArquivo("meuArq.txt", conteudo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Toast.makeText(this, "texto salvo com sucesso", Toast.LENGTH_SHORT).show();
        editText.setText("");
        Intent intent = new Intent(MainActivity.this, SegundaActivity.class);
        startActivity(intent);
    }//

    private void excluir(){
        arquivo.deletarArquivo("meuArq.txt");
        editText.setText("");
        Toast.makeText(this, "sucesso  ao excluir do arquivo",
                Toast.LENGTH_SHORT).show();
    }//

}//class