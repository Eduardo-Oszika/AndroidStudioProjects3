package com.oszika.provapdm2v2.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.oszika.provapdm2v2.ui.entity.Personagem;

import java.util.List;

@Dao
public interface AppDao {
    @Insert
    Long insertPersonagem(Personagem p);

    @Query("SELECT * FROM Personagem")
    List<Personagem> obterPersonagems();

    @Query("SELECT * FROM Personagem WHERE personagem_id = :id")
    Personagem obterPersonagemPorId(Integer id);

    @Query("SELECT *, personagem_id AS _id FROM personagem")
    Cursor selectAll();

    @Query("SELECT *, personagem_id AS _id FROM personagem WHERE personagem_id = :id")
    Cursor selectById(long id);

    @Query("DELETE FROM Personagem where personagem_id = :id")
    int deletarPersonagemPorId(Integer id);

    @Query("DELETE FROM Personagem")
    int deletarTodosPersonagems();

    @Update
    int atualizarPersonagem(Personagem p);


}
