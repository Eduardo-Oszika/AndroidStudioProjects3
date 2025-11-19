package com.oszika.exemplofragment03;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetalheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalhe);
        String nome = getIntent().getStringExtra("estrela_nome");
        String descricao = getIntent().getStringExtra("estrela_descricao");
        EstrelaFragment Fragment = EstrelaFragment.newInstance(nome, descricao);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, Fragment)
                .disallowAddToBackStack()
                .commit();
    }
}