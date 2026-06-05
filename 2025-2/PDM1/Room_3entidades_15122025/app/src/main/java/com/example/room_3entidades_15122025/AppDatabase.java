package com.example.room_3entidades_15122025;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.room_3entidades_15122025.entity.Disciplina;
import com.example.room_3entidades_15122025.entity.Estudante;
import com.example.room_3entidades_15122025.entity.EstudanteDisciplinaCrossRef;

@Database(entities = {Curso.class,
Disciplina.class,
Estudante.class, EstudanteDisciplinaCrossRef.class} , version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract AppDao appDao();
}
