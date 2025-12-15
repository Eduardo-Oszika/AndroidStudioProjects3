package com.oszika.exemploroom;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "curso")
public class Curso {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "curso_id")
    public Long cursoId;

    @NonNull
    @ColumnInfo(name = "curso_nome")
    public String cursoNome;

    public Curso() {
    }

    @Ignore
    public Curso(@NonNull String cursoNome) {
        this.cursoNome = cursoNome;
    }
}//class

