package com.example.room_3entidades_15122025;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "estudante",
    foreignKeys = {
        @ForeignKey(entity = Curso.class,
        parentColumns = "curso_id",
        childColumns = "curso_id",
        onDelete = ForeignKey.CASCADE)
    }
)
public class Estudante {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "estudante_id")
    public long estudanteId;

    @ColumnInfo(name = "estudante_nome")
    public String estudantenNome;

    @ColumnInfo(name = "curso_id")
    public long cursoId;

    public Estudante() {
    }

    @Ignore
    public Estudante(String estudantenNome, Long cursoId) {
        this.estudantenNome = estudantenNome;
        this.cursoId = cursoId;
    }
}//class
