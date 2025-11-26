package com.oszika.exemploarquivoexterno;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class SegundaActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;
    private ManipulaArquivo arquivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        textView = findViewById(R.id.textViewTexto);
        button = findViewById(R.id.buttonVoltar);
        arquivo = new ManipulaArquivo(this);

        String texto = null;
        try {
            texto = arquivo.lerArquivo("meuArq.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        textView.setText(texto);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retornar();
            }
        });
    }//onCreate

    private void retornar() {
        Intent intent = new Intent(SegundaActivity.this, MainActivity.class);
        intent.putExtra("dados",textView.getText().toString());
        startActivity(intent);
        finish();
    }//method
}//class

