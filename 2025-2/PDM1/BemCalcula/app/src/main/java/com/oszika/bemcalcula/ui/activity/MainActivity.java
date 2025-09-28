package com.oszika.bemcalcula.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.oszika.bemcalcula.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }

    public void calcularBasico(View view) {
        Intent intent = new Intent(MainActivity.this, CalculoBasicoActivity.class);
        startActivity(intent);
    }

    public void calcularIMC(View view) {
        Intent intent = new Intent(MainActivity.this, CalculoIMCActivity.class);
        startActivity(intent);
    }
}