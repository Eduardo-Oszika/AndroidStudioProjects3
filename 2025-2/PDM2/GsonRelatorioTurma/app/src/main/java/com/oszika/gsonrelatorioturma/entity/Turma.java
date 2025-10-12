package com.oszika.gsonrelatorioturma.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Turma implements Serializable {

    @SerializedName("Alunos")
    @Expose
    List<Aluno> alunos;

    public Turma(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Turma() {
        this.alunos = new ArrayList<>();
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Aluno> getAprovados() {
        List<Aluno> aprovados = new ArrayList<>();
        for (Aluno aluno : alunos) {
            if (aluno.getMedia() >= 6.0 && aluno.getFrequencia() >= 7.5) {
                aprovados.add(aluno);
            }
        }
        return aprovados;
    }

    public Double mediaIdade() {
        int somaIdade = 0;
        for (Aluno aluno : alunos) {
            somaIdade += aluno.getIdade();
        }
        return (double) (somaIdade / alunos.size());
    }

    @Override
    public String toString() {
        return "Turma{" +
                "alunos=" + alunos +
                '}';
    }
}