package com.oszika.provapdm1v2.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.oszika.provapdm1v2.entity.Pergunta;
import com.oszika.provapdm1v2.entity.Usuario;

@Database(entities = {Usuario.class, Pergunta.class} , version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract AppDao appDao();
}
