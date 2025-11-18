package com.oszika.prova1pdm2.ui;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oszika.prova1pdm2.ui.elemento.Elementos;
import com.oszika.prova1pdm2.util.Conexao;
import com.oszika.prova1pdm2.util.Conversao;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SharedViewModel extends ViewModel {

    private Elementos elementos, elementosEmbaralhados;

    public SharedViewModel() {

    }


    public Elementos getElementos() {
        return elementos;
    }

    public void setElementos(Elementos elementos) {
        this.elementos = elementos;
    }

    public Elementos getElementosEmbaralhados() {
        return elementosEmbaralhados;
    }
    public void setElementosEmbaralhados(Elementos elementosEmbaralhados) {
        this.elementosEmbaralhados = elementosEmbaralhados;
    }
}