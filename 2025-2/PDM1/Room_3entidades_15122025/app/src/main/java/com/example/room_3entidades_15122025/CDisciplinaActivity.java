package com.example.room_3entidades_15122025;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.room_3entidades_15122025.entity.Disciplina;

public class CDisciplinaActivity extends AppCompatActivity {
    private AppDao dao;
    private EditText editTextNomeDisciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cdisciplina);
        AppDatabase db = InstanciaDB.getInstance(getApplicationContext());
        dao = db.appDao();

        editTextNomeDisciplina = findViewById(R.id.et_nome_disciplina);

    }

    public void adicionarDisciplina(View view) {
        String nomeDisciplina = editTextNomeDisciplina.getText().toString();
        dao.inserirDisciplina(new Disciplina(nomeDisciplina));
    }
}