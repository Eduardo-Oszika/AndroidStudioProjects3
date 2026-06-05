package com.oszika.revisaoprovapdm2.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "resultado")
public class Resultado {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "resultado_id")
    public Long id;

    @ColumnInfo(name = "disciplina_nome")
    public String disciplina;

    @ColumnInfo(name = "aluno_nome")
    public String nomeAluno;

    @ColumnInfo(name = "aluno_nota_final")
    public Float notaFinal;

    @ColumnInfo(name = "aluno_status")
    public String status;

    public Resultado() {
    }

    @Ignore
    public Resultado(String disciplina, String nomeAluno, Float notaFinal, String status) {
        this.disciplina = disciplina;
        this.nomeAluno = nomeAluno;
        this.notaFinal = notaFinal;
        this.status = status;
    }

    @Ignore
    public Resultado(Long id, String disciplina, String nomeAluno, Float notaFinal, String status) {
        this.id = id;
        this.disciplina = disciplina;
        this.nomeAluno = nomeAluno;
        this.notaFinal = notaFinal;
        this.status = status;
    }

    @Ignore
    public String getNotaFinalFormatada() {
        return String.format("%.2f", notaFinal);
    }
}