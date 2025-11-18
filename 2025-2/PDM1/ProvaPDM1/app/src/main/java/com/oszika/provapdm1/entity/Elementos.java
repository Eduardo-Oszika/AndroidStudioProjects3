package com.oszika.provapdm1.entity;

import com.oszika.provapdm1.R;

import java.io.Serializable;

public enum Elementos implements Serializable {
    hidrogenio(R.string.hidrogenio,R.drawable.ic_elemento_hidrogenio,1),
    litio(R.string.litio,R.drawable.ic_elemento_litio,3),
    sodio(R.string.sodio,R.drawable.ic_elemento_sodio,11),
    potassio(R.string.potassio,R.drawable.ic_elemento_potassio,19),
    rubidio(R.string.rubidio,R.drawable.ic_elemento_rubidio,37),
    cesio(R.string.cesio,R.drawable.ic_elemento_cesio,55),
    francio(R.string.francio,R.drawable.ic_elemento_francio,87),
    cobre(R.string.cobre,R.drawable.ic_elemento_cobre,29),
    prata(R.string.prata,R.drawable.ic_elemento_prata,47),
    ouro(R.string.ouro,R.drawable.ic_elemento_ouro,79),
    roentgenio(R.string.roentgenio,R.drawable.ic_elemento_roentgenio,111),
    titanio(R.string.titanio,R.drawable.ic_elemento_titanio,22);


    private final int stringResId;
    private final int drawableResId;
    private final int numeroAtomico;

    Elementos(int stringResId, int drawableResId, int numeroAtomico) {
        this.stringResId = stringResId;
        this.drawableResId = drawableResId;
        this.numeroAtomico = numeroAtomico;
    }

    public int getStringResId() {
        return stringResId;
    }

    public int getDrawableResId() {
        return drawableResId;
    }
    public int getNumeroAtomico() {
        return numeroAtomico;
    }
    }
