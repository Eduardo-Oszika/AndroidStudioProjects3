package com.oszika.revisaoprovapdm2.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.oszika.revisaoprovapdm2.entity.Professor;
import com.oszika.revisaoprovapdm2.entity.Resultado;

@Database(entities = {Professor.class, Resultado.class}, version = 1,exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    public abstract AppDao appDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "escola.db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}