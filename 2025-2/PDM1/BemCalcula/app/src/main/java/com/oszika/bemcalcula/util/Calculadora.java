package com.oszika.bemcalcula.util;




import com.oszika.bemcalcula.R;


public class Calculadora {


    public Calculadora() {
    }

    public double somar(double num1, double num2) {
        return num1 + num2;
    }

    public double subtrair(double num1, double num2) {
        return num1 - num2;
    }

    public double multiplicar(double num1, double num2) {
        return num1 * num2;
    }

    public double dividir(double num1, double num2) {
        if (num2 == 0) {
            throw new IllegalArgumentException(String.valueOf(R.string.erro_divisao_por_zero));
        }
        return (double) num1 / num2;
    }

}
