package com.oszika.bemcalcula.util;

import android.content.Context;
import com.oszika.bemcalcula.R;

public enum ClassificacaoIMC {
    ABAIXO_DO_PESO(R.string.abaixo_do_peso),
    PESO_NORMAL(R.string.peso_normal),
    SOBREPESO(R.string.sobrepeso),
    OBESIDADE_GRAU_I(R.string.obesidade_grau_i),
    OBESIDADE_GRAU_II(R.string.obesidade_grau_ii),
    OBESIDADE_GRAU_III(R.string.obesidade_grau_iii);

    private final int stringResId;

    ClassificacaoIMC(int stringResId) {
        this.stringResId = stringResId;
    }

    public String getDescricao(Context context) {
        return context.getString(stringResId);
    }
}
