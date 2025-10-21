package com.oszika.prova1pdm2.ui.elemento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Elementos implements Serializable {

    @SerializedName("elementos")
    @Expose
    private List<String> nomes;
    @SerializedName("simbolos")
    @Expose
    private List<String> simbolos;
    @SerializedName("numeros-atomicos")
    @Expose
    private List<Integer> numerosAtomicos;

    /**
     * No args constructor for use in serialization
     *
     */
    public Elementos() {
        nomes = new ArrayList<>();
        simbolos = new ArrayList<>();
        numerosAtomicos = new ArrayList<>();
    }

    public Elementos(List<String> elementos, List<String> simbolos, List<Integer> numerosAtomicos) {
        super();
        this.nomes = elementos;
        this.simbolos = simbolos;
        this.numerosAtomicos = numerosAtomicos;
    }

    public List<String> getNomes() {
        return nomes;
    }

    public int getNomeLenght(int i){
        return  nomes.get(i).length();
    }

    public void setNomes(List<String> nomes) {
        this.nomes = nomes;
    }

    public List<String> getSimbolos() {
        return simbolos;
    }

    public void setSimbolos(List<String> simbolos) {
        this.simbolos = simbolos;
    }

    public List<Integer> getNumerosAtomicos() {
        return numerosAtomicos;
    }

    public void setNumerosAtomicos(List<Integer> numerosAtomicos) {
        this.numerosAtomicos = numerosAtomicos;
    }

    public String toString(int i) {
        return "Elemento: " + nomes.get(i) +
                "\nsimbolo: " + simbolos.get(i) +
                "\nnumero: " + numerosAtomicos.get(i)
                ;
    }

    public ArrayList<String> toStringList(){
        ArrayList<String> elementos = new ArrayList<>();
        for (int i = 0; i < nomes.size(); i++) {
            elementos.add(toString(i));
        }
        return elementos;
    }
}