package com.oszika.bemcalcula.util;



import android.content.res.Resources;

import com.oszika.bemcalcula.R;


public class Calculadora {

    private static final Resources resources = Resources.getSystem();

    public Calculadora() {
    }

    public double somar(int num1, int num2) {
        return num1 + num2;
    }

    public double subtrair(int num1, int num2) {
        return num1 - num2;
    }

    public double multiplicar(int num1, int num2) {
        return num1 * num2;
    }

    public double dividir(int num1, int num2) {
        if (num2 == 0) {
            throw new IllegalArgumentException(resources.getString(R.string.erro_divisao_por_zero));
        }
        return (double) num1 / num2;
    }

}
