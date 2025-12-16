package com.example.room_3entidades_15122025;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class EstudanteComDisciplinas {
    @Embedded
    public Estudante estudante;
    @Relation(
            parentColumn = "estudante_id",
            entityColumn = "disciplina_id",
            associateBy = @Junction(EstudanteDisciplinaCrossRef.class)
    )
    public List<Disciplina> disciplinas;
}
