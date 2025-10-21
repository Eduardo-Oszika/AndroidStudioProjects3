package com.oszika.prova1pdm2.ui.embaralhar;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.oszika.prova1pdm2.ui.elemento.Elementos;

import java.util.ArrayList;

public class EmbaralharViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<String>> lista;
    Elementos elementos;

    public EmbaralharViewModel() {
        elementos = new Elementos();
        lista = new MutableLiveData<>();
    }

    private void embaralhar() {
        elementos.getNumerosAtomicos();
        Elementos elementos1 = new Elementos();
        elementos1.setNomes(elementos.getNomes());
        elementos1.setSimbolos(elementos.getSimbolos());
        for (int i = 0; i < elementos.getNumerosAtomicos().size(); i++) {
            elementos1.getNumerosAtomicos().add(elementos.getNumerosAtomicos().get(i) + elementos.getNomeLenght(i));
        }
        lista.setValue(elementos1.toStringList());
    }

    public void setElementos(Elementos elementos) {
        this.elementos = elementos;
        embaralhar();
    }

    public MutableLiveData<ArrayList<String>> getElementosEmbaralhados() {
        return lista;
    }
}