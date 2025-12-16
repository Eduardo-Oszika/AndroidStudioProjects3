package com.example.room_3entidades_15122025;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {
    private TextView textView1, textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.textViewResultado1);
        textView2 = findViewById(R.id.textViewResultado2);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "escola.db").allowMainThreadQueries().build();
        AppDao dao = db.appDao();
        manipulaBanco(dao);
    }

    private void manipulaBanco(AppDao dao) {
        long cursoId = dao.inserirCurso(new Curso("Sistemas para internet"));
        long disc1Id = dao.inserirDisciplina(new Disciplina("Android 1"));
        long disc2Id = dao.inserirDisciplina(new Disciplina("IA"));
        long estudante1ID = dao.inserirEstudante(new Estudante("Marcos",cursoId));
        long estudante2ID = dao.inserirEstudante(new Estudante("Ana",cursoId));

        dao.inserirEstudanteDisciplina(new EstudanteDisciplinaCrossRef(estudante1ID, disc1Id));
        dao.inserirEstudanteDisciplina(new EstudanteDisciplinaCrossRef(estudante1ID, disc2Id));
        dao.inserirEstudanteDisciplina(new EstudanteDisciplinaCrossRef(estudante2ID, disc1Id));

        EstudanteComDisciplinas ec = dao.obterEstudanteComDisciplinas(estudante1ID);
        StringBuilder dados = new StringBuilder();
        dados.append("Aluno e Disciplina: ");
        dados.append("\n");
        dados.append(ec.estudante.estudantenNome);
        for(Disciplina d: ec.disciplinas){
            dados.append("Disciplina: ");
            dados.append(d.disciplinaNome);
            dados.append("\n");
        }//for
        textView1.setText(dados.toString());

        dados = new StringBuilder();
        DisciplinaComEstudantes dc = dao.obterDisciplinaComEstudantes(disc1Id);
        dados.append("Disciplina e Aluno");
        dados.append("\n");
        dados.append(dc.disciplina.disciplinaNome);
        for(Estudante e:dc.estudantes){
            dados.append("Estudante:");
            dados.append(e.estudantenNome);
            dados.append("\n");

        }//for
        textView2.setText(dados.toString());


    }//
}