package com.oszika.exemploroom;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AppDAO {
    @Insert
    Long inserirCurso(Curso c);
    @Insert
    Long inserirDisciplina(Disciplinas d);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long inserirEstudante(Estudante e);
    @Query("SELECT * FROM curso")
    List<Curso> obterCursos();
    @Query("SELECT * FROM disciplinas")
    List<Disciplinas> obterDisciplinas();
    @Query("SELECT * FROM estudante")
    List<Estudante> obterEstudantes();
}
