package com.oszika.exemplolanchonete;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ImageView imgCafe, imgBolo;
    private TextView textViewPreco;
    private Calculadora calculadora;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        intent = getIntent();


        imgCafe = findViewById(R.id.ivCafe);
        imgBolo = findViewById(R.id.ivBolo);
        textViewPreco = findViewById(R.id.tvPreco);
        calculadora = new Calculadora();

        if (intent.getDoubleExtra("total", 0) != 0) {
            double total = intent.getDoubleExtra("total", 0);
            textViewPreco.setText("Total: R$ " + String.format("%.2f", total));
        }

        imgCafe.setOnClickListener(v -> {
//            Snackbar.make(v, "Café selecionado", Snackbar.LENGTH_SHORT).show();
            intent = new Intent(MainActivity.this, CafeActivity.class);
            intent.putExtra("total", getIntent().getDoubleExtra("total", 0));
            startActivity(intent);
        });
        imgBolo.setOnClickListener(v -> {
//            Snackbar.make(v, "Bolo selecionado", Snackbar.LENGTH_SHORT).show();
            intent = new Intent(MainActivity.this, BoloActivity.class);
            intent.putExtra("total", getIntent().getDoubleExtra("total", 0));
            startActivity(intent);
        });

    }
}