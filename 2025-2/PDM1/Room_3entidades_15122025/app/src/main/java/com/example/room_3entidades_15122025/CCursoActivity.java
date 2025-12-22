package com.example.room_3entidades_15122025;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.room_3entidades_15122025.entity.Curso;

public class CCursoActivity extends AppCompatActivity {
    private AppDao dao;
    private EditText editTextNomeCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ccurso);
        AppDatabase db = InstanciaDB.getInstance(getApplicationContext());
        dao = db.appDao();

        editTextNomeCurso = findViewById(R.id.et_nome_curso);

    }

    public void adicionarCurso(View view) {
        String nomeCurso = editTextNomeCurso.getText().toString();
        dao.inserirCurso(new Curso(nomeCurso));
    }
}