package com.oszika.exemploroom;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "disciplinas")
public class Disciplinas {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "disciplina_id")
    public Long disciplinaId;

    @NonNull
    @ColumnInfo(name = "disciplina_nome")
    public String disciplinaNome;

    public Disciplinas() {
    }

    @Ignore
    public Disciplinas( @NonNull String disciplinaNome) {
        this.disciplinaNome = disciplinaNome;
    }
}
