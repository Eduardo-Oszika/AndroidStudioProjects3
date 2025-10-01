package com.oszika.exemplolistapersonalizada;

public class ItemLista {
    private String nome;
    private int imagem;

    public ItemLista(String nome, int imagem) {
        this.imagem = imagem;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
