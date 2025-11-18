package com.oszika.prova1pdm2.ui.home;

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

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<String>> lista;
    private Elementos elementos;
    private final String URL = "https://my-json-server.typicode.com/Eduardo-Oszika/AndroidStudioProjects3/db";
    private ExecutorService executorService;
    private Handler mainHandler;

    public HomeViewModel() {
        lista = new MutableLiveData<>();
        executorService = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());
        obterDados();
    }

    public MutableLiveData<ArrayList<String>> getElementosText() {
        return lista;
    }

    public Elementos getElementos() {
        return elementos;
    }

    private void obterDados() {
        executorService.execute(() -> {
            try {
                Conexao conexao = new Conexao();
                InputStream inputStream = conexao.obterRespostaHTTP(URL);
                Conversao conversao = new Conversao();
                String textoJSON = conversao.converter(inputStream);
                Log.i("JSON", "doInBackground: " + textoJSON);
                if (textoJSON != null) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<Elementos>() {
                    }.getType();
                    elementos = gson.fromJson(textoJSON, type);
                }
                mainHandler.post(() -> {
                    if (elementos.getNomes().isEmpty() == false) {
                        lista.setValue(elementos.toStringList());
                    } else {
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }
}