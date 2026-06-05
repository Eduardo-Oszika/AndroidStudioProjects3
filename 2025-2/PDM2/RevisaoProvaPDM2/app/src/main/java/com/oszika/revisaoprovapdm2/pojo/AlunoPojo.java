package com.oszika.revisaoprovapdm2.pojo;

import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class AlunoPojo {

    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("nota1")
    @Expose
    private Float nota1;
    @SerializedName("nota2")
    @Expose
    private Float nota2;
    @SerializedName("frequencias")
    @Expose
    private List<Boolean> frequencias;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AlunoPojo() {
    }

    public AlunoPojo(String nome, Float nota1, Float nota2, List<Boolean> frequencias) {
        super();
        this.nome = nome;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.frequencias = frequencias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getNota1() {
        return nota1;
    }

    public void setNota1(Float nota1) {
        this.nota1 = nota1;
    }

    public Float getNota2() {
        return nota2;
    }

    public void setNota2(Float nota2) {
        this.nota2 = nota2;
    }

    public List<Boolean> getFrequencias() {
        return frequencias;
    }

    public void setFrequencias(List<Boolean> frequencias) {
        this.frequencias = frequencias;
    }

    public String getStatus() {
        float notaFinal = getNotaFinal();
        int totalAulas = this.frequencias.size();
        int aulasAssistidas = 0;
        for (Boolean presente : this.frequencias) {
            if (presente) {
                aulasAssistidas++;
            }
        }
        float frequenciaPercentual = ((float) aulasAssistidas / totalAulas) * 100;
        if (notaFinal >= 6.0 && frequenciaPercentual >= 75.0)
            return "Aprovado";

        return "Reprovado";
    }

    public float getNotaFinal() {
        return (this.nota1 + this.nota2) / 2;
    }


}
