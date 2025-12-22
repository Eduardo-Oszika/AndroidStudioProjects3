package com.example.room_3entidades_15122025;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.room_3entidades_15122025.entity.Curso;
import com.example.room_3entidades_15122025.entity.Disciplina;
import com.example.room_3entidades_15122025.entity.DisciplinaComEstudantes;
import com.example.room_3entidades_15122025.entity.Estudante;
import com.example.room_3entidades_15122025.entity.EstudanteComDisciplinas;
import com.example.room_3entidades_15122025.entity.EstudanteDisciplinaCrossRef;
import com.example.room_3entidades_15122025.entity.User;

import java.util.List;

@Dao
public interface AppDao {
    @Insert
    Long inserirCurso(Curso c);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long inserirUser(User u);

    @Insert
    Long inserirDisciplina(Disciplina d);

    @Insert
    Long inserirEstudante(Estudante e);

    @Insert
    void inserirEstudanteDisciplina(EstudanteDisciplinaCrossRef ed);

    @Query("SELECT * FROM estudante")
    List<Estudante> obterEstudantes();

    @Query("SELECT * FROM user")
    List<User> obterUsuario();

    @Query("SELECT * FROM disciplina")
    List<Disciplina> obterDisciplinas();

    @Query("SELECT * FROM curso")
    List<Curso> obterCursos();

    @Query("SELECT * FROM estudante WHERE estudante_id = :id")
    EstudanteComDisciplinas obterEstudanteComDisciplinas(long id);

    @Query("SELECT * FROM disciplina WHERE disciplina_id = :id")
    DisciplinaComEstudantes obterDisciplinaComEstudantes(long id);

    @Query("Select * from user where user_nome = :nome and user_senha = :senha")
    User autenticar(String nome, String senha);
}
