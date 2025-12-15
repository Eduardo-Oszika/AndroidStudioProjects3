package com.oszika.exemploroom;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textViewResultado = findViewById(R.id.textViewResultado);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,
                "escola.db").allowMainThreadQueries().build();

        AppDAO dao = db.appDAO();
        long cursoId = dao.inserirCurso(new Curso("Sistemas para Internet"));
        long disciplinaId = dao.inserirDisciplina(new Disciplinas("Android 1"));
        dao.inserirEstudante(new Estudante("Eduardo Oszika", cursoId, disciplinaId));
        dao.inserirEstudante(new Estudante("Eduardo Oszika", cursoId, disciplinaId));


        List<Estudante> estudantes = dao.obterEstudantes();
        StringBuilder resultado = new StringBuilder();
        for (Estudante e : estudantes) {
            resultado.append(" - Nome: ").append(e.estudanteNome)
                    .append(" - Curso ID: ").append(e.cursoId)
                    .append(" - Disciplina ID: ").append(e.disciplinaId)
                    .append("\n");
        }
        textViewResultado.setText(resultado.toString());
    }
}