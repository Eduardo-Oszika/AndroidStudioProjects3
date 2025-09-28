package com.oszika.bemcalcula.util;

import android.content.res.Resources;

import com.oszika.bemcalcula.R;

public class CalculadoraIMC {
    private static final Resources resources = Resources.getSystem();

    public double calcularIMC(double peso, double altura) {
        if (altura <= 0) {
            throw new IllegalArgumentException(resources.getString(R.string.erro_altura_invalida));
        }
        if (peso <= 0) {
            throw new IllegalArgumentException(resources.getString(R.string.erro_peso_invalido));
        }
        return peso / (altura * altura);
    }

    public ClassificacaoIMC classificarIMC(double imc) {
        if (imc < 18.5) return ClassificacaoIMC.ABAIXO_DO_PESO;
        if (imc < 25) return ClassificacaoIMC.PESO_NORMAL;
        if (imc < 30) return ClassificacaoIMC.SOBREPESO;
        if (imc < 35) return ClassificacaoIMC.OBESIDADE_GRAU_I;
        if (imc < 40) return ClassificacaoIMC.OBESIDADE_GRAU_II;
        return ClassificacaoIMC.OBESIDADE_GRAU_III;
    }
}
