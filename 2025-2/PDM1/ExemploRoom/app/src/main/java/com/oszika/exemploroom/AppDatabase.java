package com.oszika.exemploroom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Curso.class, Disciplinas.class, Estudante.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AppDAO appDAO();

}
