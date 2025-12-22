package com.example.room_3entidades_15122025.entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class DisciplinaComEstudantes {
    @Embedded
    public Disciplina disciplina;
    @Relation(
            parentColumn = "disciplina_id",
            entityColumn = "estudante_id",
            associateBy = @Junction(EstudanteDisciplinaCrossRef.class)
    )
    public List<Estudante> estudantes;

}//class
