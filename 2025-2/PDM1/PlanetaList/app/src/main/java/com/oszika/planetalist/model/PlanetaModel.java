package com.oszika.planetalist.model;


import android.graphics.drawable.Drawable;

public class PlanetaModel {
    String nome;
    Double gravidade;
    int imagem;

    public PlanetaModel(String nome, Double gravidade, int imagem) {
        this.nome = nome;
        this.gravidade = gravidade;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getGravidade() {
        return gravidade;
    }

    public void setGravidade(Double gravidade) {
        this.gravidade = gravidade;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return "PlanetaModel{" +
                "nome='" + nome + '\'' +
                ", gravidade=" + gravidade +
                ", imagem=" + imagem +
                '}';
    }
}
