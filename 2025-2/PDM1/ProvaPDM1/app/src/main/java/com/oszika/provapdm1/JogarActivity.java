package com.oszika.provapdm1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class JogarActivity extends AppCompatActivity {

    EditText etNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jogar);
        etNumero = findViewById(R.id.et_numero_digitado);

    }

    public void clicou(View view) {
        Toast.makeText(this, etNumero.getText(), Toast.LENGTH_SHORT).show();
    }
}