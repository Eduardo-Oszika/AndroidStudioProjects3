package com.oszika.provapdm2v2.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.oszika.provapdm2v2.ui.entity.Personagem;
import com.oszika.provapdm2v2.ui.entity.PersonagemDAO;

import java.util.List;

@Dao
public interface AppDao {
    @Insert
    Long insertPersonagem(PersonagemDAO p);
    @Query("SELECT * FROM personagem")
    List<PersonagemDAO> obterPersonagems();

}
