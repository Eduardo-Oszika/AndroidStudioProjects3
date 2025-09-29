package com.oszika.exemplocalculadoramvvm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculadoraViewModel extends ViewModel {
    private Calculadora calculadora;
    private MutableLiveData<String> resultado;

    public CalculadoraViewModel(){
        this.calculadora = new Calculadora();
        this.resultado = new MutableLiveData<>();
    }

    public MutableLiveData<String> getResultado() {
        return resultado;
    }

    public void calcularVM(double n1, double n2, String op){
        double res = calculadora.calcular(n1, n2, op);
        resultado.setValue("Resultado: " +res);
    }
}
