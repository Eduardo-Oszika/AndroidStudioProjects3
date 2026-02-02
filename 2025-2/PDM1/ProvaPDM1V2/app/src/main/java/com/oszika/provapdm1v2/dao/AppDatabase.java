package com.oszika.provapdm1v2.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.oszika.provapdm1v2.entity.Pergunta;
import com.oszika.provapdm1v2.entity.Usuario;

@Database(entities = {Usuario.class, Pergunta.class} , version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract AppDao appDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "escola.db")
                            // Permite consultas na thread principal (necessário se o ContentProvider
                            // for chamado na UI Thread e você não estiver usando Loaders/Coroutines no cliente)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}