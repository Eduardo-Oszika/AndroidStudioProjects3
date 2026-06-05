package com.oszika.revisaoprovapdm2.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.oszika.revisaoprovapdm2.entity.Professor;
import com.oszika.revisaoprovapdm2.entity.Resultado;

import java.util.List;

@Dao
public interface AppDao {

    @Insert
    long insertProfessor(Professor professor);

    @Query("SELECT * FROM professor")
    List<Professor> obterProfessores();

    @Query("SELECT * FROM professor WHERE professor_id = :id")
    Professor obterProfessorPorId(Long id);

    @Query("SELECT * FROM professor WHERE professor_username = :username AND professor_senha = :senha LIMIT 1")
    Professor login(String username, String senha);

    @Query("DELETE FROM professor WHERE professor_id = :id")
    int deletarProfessorPorId(Long id);

    @Query("DELETE FROM professor")
    int deletarTodosProfessores();

    @Update
    int atualizarProfessor(Professor professor);

    @Query("SELECT professor_id AS _id, professor_username, professor_senha FROM professor")
    Cursor selectAllProfessoresCursor();

    @Query("SELECT professor_id AS _id, professor_username, professor_senha FROM professor WHERE professor_id = :id")
    Cursor selectProfessorByIdCursor(long id);

    @Query("SELECT professor_id AS _id, professor_username, professor_senha FROM professor WHERE professor_username = :username AND professor_senha = :senha LIMIT 1")
    Cursor loginCursor(String username, String senha);


    @Insert
    long insertResultado(Resultado resultado);

    @Query("SELECT * FROM resultado")
    List<Resultado> obterResultados();

    @Query("SELECT * FROM resultado WHERE resultado_id = :id")
    Resultado obterResultadoPorId(Long id);

    @Query("DELETE FROM resultado WHERE resultado_id = :id")
    int deletarResultadoPorId(Long id);

    @Query("DELETE FROM resultado")
    int deletarTodosResultados();

    @Update
    int atualizarResultado(Resultado resultado);


    @Query("SELECT resultado_id AS _id, disciplina_nome, aluno_nome, aluno_nota_final, aluno_status FROM resultado")
    Cursor selectAllResultadosCursor();

    @Query("SELECT resultado_id AS _id, disciplina_nome, aluno_nome, aluno_nota_final, aluno_status FROM resultado WHERE resultado_id = :id")
    Cursor selectResultadoByIdCursor(long id);

}
