package com.oszika.exemplocalculadoramvvm;

public class Calculadora {
    public double calcular(double n1, double n2, String op) {
        switch (op) {
            case "+":
                return n1 + n2;
            case "-":
                return n1 - n2;
            case "*":
                return n1 * n2;
            case "/":


                return n1 / n2;


            default:
                return 0;
        }//switch
    }//method
}//class