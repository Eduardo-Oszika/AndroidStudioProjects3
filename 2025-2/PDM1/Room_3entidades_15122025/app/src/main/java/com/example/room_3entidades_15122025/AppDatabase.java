package com.example.room_3entidades_15122025;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Curso.class,
Disciplina.class,
Estudante.class, EstudanteDisciplinaCrossRef.class} , version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract AppDao appDao();
}
