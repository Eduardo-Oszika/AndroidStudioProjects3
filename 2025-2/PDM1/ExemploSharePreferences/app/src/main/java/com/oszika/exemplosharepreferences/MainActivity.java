package com.oszika.exemplosharepreferences;

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

    private EditText editTextNome;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editTextNome = findViewById(R.id.etNome);
        button = findViewById(R.id.btnNext);
    }

    public void clicar(View view) {
        String nome = editTextNome.getText().toString();
        let(nome);
    }

    private void let(String nome) {
        Intent it = null;
        if (SharePreferencesUtil.contemDados(this)) {
            String nomeSalvo = SharePreferencesUtil.obterNomeUsuario(this);
            if (nomeSalvo.equals(nome))
                it = new Intent(this, TerceiraActivity.class);
            else {
                it = new Intent(this, SegundaActivity.class);
                it.putExtra("nome", nome);
            }
            startActivity(it);
        } else {
            it = new Intent(this, SegundaActivity.class);
            it.putExtra("nome", nome);
            startActivity(it);
        }
    }
}