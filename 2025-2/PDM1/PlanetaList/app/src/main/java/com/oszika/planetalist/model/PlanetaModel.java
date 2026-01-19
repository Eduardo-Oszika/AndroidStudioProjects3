package com.oszika.planetalist.model;


public class PlanetaModel {
    String nome;
    Double gravidade;
    int imagem;
    Double pesoNoPlaneta;
    Double massaNoPlaneta;


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

    public String getStringMassaEmkg(Double massa) {
        this.massaNoPlaneta = (massa / 9.81) * this.gravidade;
        String massaNoPlaneta = String.format("%.2f", this.massaNoPlaneta);
        return "Massa no planeta: " + massaNoPlaneta + " kg";
    }

    public String getStringPessoEmNewton(Double massa) {
        this.pesoNoPlaneta = massa * this.gravidade;
        String pesoNoPlaneta = String.format("%.2f", this.pesoNoPlaneta);
        return "Peso no planeta: " + pesoNoPlaneta + " N";
    }

    public Double getPesoNoPlaneta() {
        return pesoNoPlaneta;
    }

    public Double getMassaNoPlaneta() {
        return massaNoPlaneta;
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
