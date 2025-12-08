package com.oszika.quebrasono.entity;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Jogo {

    @SerializedName("cartelas")
    @Expose
    private List<List<String>> cartelas;
    @SerializedName("combinacao_premiada")
    @Expose
    private List<String> combinacaoPremiada;

    /**
     * No args constructor for use in serialization
     *
     */
    public Jogo() {
    }

    public Jogo(List<List<String>> cartelas, List<String> combinacaoPremiada) {
        super();
        this.cartelas = cartelas;
        this.combinacaoPremiada = combinacaoPremiada;
    }

    public List<List<String>> getCartelas() {
        return cartelas;
    }

    public void setCartelas(List<List<String>> cartelas) {
        this.cartelas = cartelas;
    }

    public List<String> getCombinacaoPremiada() {
        return combinacaoPremiada;
    }

    public void setCombinacaoPremiada(List<String> combinacaoPremiada) {
        this.combinacaoPremiada = combinacaoPremiada;
    }

}
