package com.oszika.bemcalcula.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;
import com.oszika.bemcalcula.R;

public class MainActivity extends AppCompatActivity {
    RadioButton rbBasico, rbIMC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        rbBasico = findViewById(R.id.rbCalculo);
        rbIMC = findViewById(R.id.rbIMC);
    }
    public void abrirOutraTela(View view) {
        if (rbBasico.isChecked()) {
            calcularBasico();
        } else if (rbIMC.isChecked()) {
            calcularIMC();
        }else
            Snackbar.make(view, "Selecione uma opção", Snackbar.LENGTH_SHORT).show();
    }

    public void calcularBasico() {
        Intent intent = new Intent(MainActivity.this, CalculoBasicoActivity.class);
        startActivity(intent);
    }

    public void calcularIMC() {
        Intent intent = new Intent(MainActivity.this, CalculoIMCActivity.class);
        startActivity(intent);
    }


}