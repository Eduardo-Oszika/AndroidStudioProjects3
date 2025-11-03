package com.oszika.provapdm1;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ElementoActivity extends AppCompatActivity {
    ImageView img;
    TextView tvNome,tvNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_elemento);
        img = findViewById(R.id.iv_elemento);
        tvNome = findViewById(R.id.tv_elemento_nome);
        tvNumero = findViewById(R.id.tv_elemento_numero);

        Elementos elementos = getIntent().getSerializableExtra("elementoENUM", Elementos.class);
        img.setImageDrawable(getDrawable(elementos.getDrawableResId()));
        tvNome.setText("Nome: " + getString(elementos.getStringResId()));
        tvNumero.setText("Numero: " + elementos.getNumeroAtomico());
    }
}