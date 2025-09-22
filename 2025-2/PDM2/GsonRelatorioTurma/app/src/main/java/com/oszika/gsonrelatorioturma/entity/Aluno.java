package com.oszika.gsonrelatorioturma.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Aluno {

    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("idade")
    @Expose
    private Integer idade;
    @SerializedName("nota")
    @Expose
    private List<Float> notas;
    @SerializedName("frequencia")
    @Expose
    private List<Integer> frequencia;

    /**
     * No args constructor for use in serialization
     */
    public Aluno() {
    }

    public Aluno(String nome, Integer idade, List<Float> notas, List<Integer> frequencia) {
        super();
        this.nome = nome;
        this.idade = idade;
        this.notas = notas;
        this.frequencia = frequencia;
    }

    public int getFrequencia() {
        int frequenciaAluno = 0;
        for (Integer i : frequencia) {
            frequenciaAluno += i;
        }
        return frequenciaAluno;
    }

    public Float getMedia(){
        Float media = 0.00F;
        for (Float nota : notas) {
            media += nota;
        }
        return media/3;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public List<Float> getNota() {
        return notas;
    }

    public void setNota(List<Float> notas) {
        this.notas = notas;
    }

    public void setFrequencia(List<Integer> frequencia) {
        this.frequencia = frequencia;
    }



    @Override
    public String toString() {
        return
                "nome= '" + nome + '\'' +
                ", idade= " + idade +
                ", frequencia= " + getFrequencia()+
                ", notas= " + getNota();
    }
}
