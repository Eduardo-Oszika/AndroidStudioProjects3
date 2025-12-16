package com.example.room_3entidades_15122025;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "estudante_disciplina",
        primaryKeys = {"estudante_id", "disciplina_id"},
        foreignKeys = {
            @ForeignKey(entity = Estudante.class,
                    parentColumns = "estudante_id",
                    childColumns = "estudante_id",
                    onDelete = ForeignKey.CASCADE
            ),

                @ForeignKey(entity = Disciplina.class,
                        parentColumns = "disciplina_id",
                        childColumns = "disciplina_id",
                        onDelete = ForeignKey.CASCADE
                ),

        }
)
public class EstudanteDisciplinaCrossRef {
    @ColumnInfo(name="estudante_id")
    public long estudanteId;
    @ColumnInfo(name = "disciplina_id")
    public long disciplinaId;

    public EstudanteDisciplinaCrossRef(long estudanteId, long disciplinaId) {
        this.estudanteId = estudanteId;
        this.disciplinaId = disciplinaId;
    }
}//clas
