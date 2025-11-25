package com.oszika.exemploarquivointerno;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SegundaActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;
    private ManipularArquivo arquivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_segunda);
        textView = findViewById(R.id.textViewTexto);
        button = findViewById(R.id.buttonVoltar);
        arquivo = new ManipularArquivo(this);

        String texto = arquivo.lerArquivo("arquivo_interno.txt");
        textView.setText(texto);

        button.setOnClickListener(view -> retornar());
    }

    private void retornar() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("dados", textView.getText().toString());
        startActivity(intent);
        finish();
    }
}