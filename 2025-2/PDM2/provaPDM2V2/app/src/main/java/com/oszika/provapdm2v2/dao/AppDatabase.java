package com.oszika.provapdm2v2.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.oszika.provapdm2v2.ui.entity.Personagem;
import com.oszika.provapdm2v2.ui.entity.PersonagemDAO;

@Database(entities = {PersonagemDAO.class} , version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract AppDao appDao();
}
