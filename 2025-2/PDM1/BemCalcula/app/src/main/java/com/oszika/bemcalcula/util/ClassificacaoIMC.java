package com.oszika.bemcalcula.util;

import com.oszika.bemcalcula.R;

public enum ClassificacaoIMC {
    ABAIXO_DO_PESO(R.string.abaixo_do_peso,R.drawable.abaixo_do_peso),
    PESO_NORMAL(R.string.peso_normal,R.drawable.peso_normal),
    SOBREPESO(R.string.sobrepeso,R.drawable.sobrepeso),
    OBESIDADE_GRAU_I(R.string.obesidade_grau_i,R.drawable.obesidade_1),
    OBESIDADE_GRAU_II(R.string.obesidade_grau_ii,R.drawable.obesidade_2),
    OBESIDADE_GRAU_III(R.string.obesidade_grau_iii,R.drawable.obesidade_3);

    private final int stringResId;
    private final int drawableResId;

    ClassificacaoIMC(int stringResId, int drawableResId) {
        this.stringResId = stringResId;
        this.drawableResId = drawableResId;
    }

    public int getStringResId() {
        return stringResId;
    }

    public int getDrawableResId() {
        return drawableResId;
    }
}
