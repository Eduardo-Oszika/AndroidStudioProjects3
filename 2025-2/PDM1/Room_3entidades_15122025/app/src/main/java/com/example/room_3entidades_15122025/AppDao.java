package com.example.room_3entidades_15122025;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AppDao {
    @Insert
    Long inserirCurso(Curso c);
    @Insert
    Long inserirDisciplina(Disciplina d);
    @Insert
    Long inserirEstudante(Estudante e);
    @Insert
    void inserirEstudanteDisciplina(EstudanteDisciplinaCrossRef ed);

    @Query("SELECT * FROM estudante")
    List<Estudante> obterEstudantes();
    @Query("SELECT * FROM disciplina")
    List<Disciplina> obterDisciplinas();
    @Query("SELECT * FROM curso")
    List<Curso> obterCursos();

    @Query("SELECT * FROM estudante WHERE estudante_id = :id")
    EstudanteComDisciplinas obterEstudanteComDisciplinas(long id);
    @Query("SELECT * FROM disciplina WHERE disciplina_id = :id")
    DisciplinaComEstudantes obterDisciplinaComEstudantes(long id);
}
