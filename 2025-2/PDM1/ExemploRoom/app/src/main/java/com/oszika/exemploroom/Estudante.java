package com.oszika.exemploroom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "estudante",
        foreignKeys = {
        @ForeignKey(entity = Curso.class,
                parentColumns = "curso_id",
                childColumns = "curso_id",
        onDelete = ForeignKey.CASCADE),

        @ForeignKey(entity = Disciplinas.class,
                parentColumns = "disciplina_id",
                childColumns = "disciplina_id",
        onDelete = ForeignKey.CASCADE)},
        indices = {
        @Index(value = {"estudante_nome","curso_id", "disciplina_id"}, unique = true),
        }
)
public class Estudante {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "estudante_id")
    public Long estudanteId;

    @ColumnInfo(name = "estudante_nome")
    public String estudanteNome;

    @ColumnInfo(name = "curso_id", index = true)
    public Long cursoId;

    @ColumnInfo(name = "disciplina_id", index = true)
    public Long disciplinaId;

    public Estudante() {
    }

    @Ignore
    public Estudante(String estudanteNome, Long cursoId, Long disciplinaId) {
        this.estudanteNome = estudanteNome;
        this.cursoId = cursoId;
        this.disciplinaId = disciplinaId;
    }
}
