package com.oszika.exemplovideoviewtexttospeach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.buttonProximo);
        editText = findViewById(R.id.editTextNome);

    }

    public void clicar(View view) {
        String nome = editText.getText().toString();
        Intent intent = new Intent(this, SegundaActivity.class);
        intent.putExtra("nome", nome);
        startActivity(intent);
    }

}