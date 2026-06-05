package com.oszika.revisaoprovapdm2.pojo;

import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class DisciplinaPojo {

    @SerializedName("disciplina")
    @Expose
    private String disciplina;
    @SerializedName("alunos")
    @Expose
    private List<AlunoPojo> alunos;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DisciplinaPojo() {
    }

    public DisciplinaPojo(String disciplina, List<AlunoPojo> alunos) {
        super();
        this.disciplina = disciplina;
        this.alunos = alunos;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public List<AlunoPojo> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<AlunoPojo> alunos) {
        this.alunos = alunos;
    }

}
