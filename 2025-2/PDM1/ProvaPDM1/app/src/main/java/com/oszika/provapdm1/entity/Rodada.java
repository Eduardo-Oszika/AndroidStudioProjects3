package com.oszika.provapdm1.entity;

import java.io.Serializable;

public class Rodada implements Serializable {
    Boolean acertou;

    public Rodada(Boolean acertou) {
        this.acertou = acertou;
    }

    public Boolean getAcertou() {
        return acertou;
    }

    public void setAcertou(Boolean acertou) {
        this.acertou = acertou;
    }
}
