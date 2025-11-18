package com.oszika.provapdm1.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {

    private List<Rodada> rodadas;

    public Usuario() {
        rodadas = new ArrayList<>();
    }

    public Usuario(List<Rodada> rodadas) {
        this.rodadas = rodadas;
    }
    public List<Rodada> getRodadas() {
        return rodadas;
    }

    public void addRodad(Rodada rodada) {
        this.rodadas.add(rodada);
    }

    public void setRodadas(List<Rodada> rodadas) {
        this.rodadas = rodadas;
    }
}
