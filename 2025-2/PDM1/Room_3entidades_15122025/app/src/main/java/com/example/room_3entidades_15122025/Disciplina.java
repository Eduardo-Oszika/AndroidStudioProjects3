package com.example.room_3entidades_15122025;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "disciplina")
public class Disciplina {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "disciplina_id")
    public long disciplinaId;
    @NonNull
    @ColumnInfo(name = "disciplina_nome")
    public String disciplinaNome;

    public Disciplina() {
    }

    @Ignore
    public Disciplina( @NonNull String disciplinaNome) {
        this.disciplinaNome = disciplinaNome;
    }


}
