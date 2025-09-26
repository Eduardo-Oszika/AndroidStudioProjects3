package com.oszika.exemplolanchonete;

public class Calculadora {
    private static final double PRECO_CAFE = 2.50;
    private static final double PRECO_BOLO = 5.00;

    public Calculadora() {
    }

    public double calcularTotal(int quantidadeCafe, int quantidadeBolo) {
        return (quantidadeCafe * PRECO_CAFE) + (quantidadeBolo * PRECO_BOLO);
    }

    public double calcularTotalCafe(int quantidadeCafe) {
        return quantidadeCafe * PRECO_CAFE;
    }

    public double calcularTotalBolo(int quantidadeBolo) {
        return quantidadeBolo * PRECO_BOLO;
    }

    public double getPrecoCafe() {
        return PRECO_CAFE;
    }

    public double getPrecoBolo() {
        return PRECO_BOLO;
    }
}
