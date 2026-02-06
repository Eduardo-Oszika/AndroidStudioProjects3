package com.oszika.exemplosharepreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TerceiraActivity extends AppCompatActivity {

    private View layou3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_terceira);
        layou3 = findViewById(R.id.layout3);
        ler();
    }

    private void ler() {
        if (SharePreferencesUtil.contemDados(this)) {
            String nome = SharePreferencesUtil.obterNomeUsuario(this);
            String cor = SharePreferencesUtil.obterCorFundo(this);
            Corutil.setCor(layou3, cor, this);
        } else {
            Toast.makeText(this, "Erro no arquivo", Toast.LENGTH_SHORT).show();
        }
    }
}