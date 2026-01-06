package com.oszika.quebrasono.activity.resultado;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oszika.quebrasono.R;
import com.oszika.quebrasono.entity.Jogo;
import com.oszika.quebrasono.util.Conexao;
import com.oszika.quebrasono.util.Conversao;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ResultadoViewModel extends ViewModel {

    //    private final MutableLiveData<ArrayList<String>> lista;
    private String TAG = "ResultadoViewModel";
    private Jogo jogo;
    private MutableLiveData<Jogo> jogolive;
    private MutableLiveData<ArrayList<RadioButton>> radiosLive;
    private final String URL = "https://my-json-server.typicode.com/Eduardo-Oszika/AndroidStudioProjects3/db";
    private ExecutorService executorService;
    private Handler mainHandler;
    private MutableLiveData<ArrayList<TextView>> textViewsLive, textViewsSorteadosLive;

    public ResultadoViewModel() {
        // lista = new MutableLiveData<>();
        jogo = new Jogo();
        jogolive = new MutableLiveData<>();
        radiosLive = new MutableLiveData<>();
        textViewsLive = new MutableLiveData<>();
        textViewsSorteadosLive = new MutableLiveData<>();
        executorService = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());
        obterDados();
    }

    public MutableLiveData<Jogo> getJogoLive() {
        return jogolive;
    }

    public MutableLiveData<ArrayList<TextView>> getTextViewsLive() {
        return textViewsLive;
    }

    public MutableLiveData<ArrayList<TextView>> getTextViewsSorteadosLive() {
        return textViewsSorteadosLive;
    }

    public void setTextViewsLive(ArrayList<TextView> textViews) {
        textViewsLive.setValue(textViews);
    }

    public void setTextViewsSorteadosLive(ArrayList<TextView> textViews) {
        textViewsSorteadosLive.setValue(textViews);
    }

    public MutableLiveData<ArrayList<RadioButton>> getRadiosLive() {
        return radiosLive;
    }

    public void setRadiosLive(ArrayList<RadioButton> radioButtons) {
        radiosLive.setValue(radioButtons);
    }

    private void obterDados() {
        executorService.execute(() -> {
            try {
                Conexao conexao = new Conexao();
                InputStream inputStream = conexao.obterRespostaHTTP(URL);
                Conversao conversao = new Conversao();
                String textoJSON = conversao.converter(inputStream);
                inputStream.close();
                //Log.i("JSON", "doInBackground: " + textoJSON);
                if (textoJSON != null) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<Jogo>() {
                    }.getType();
                    jogo = gson.fromJson(textoJSON, type);
                }
                mainHandler.post(() -> {
                    if (!jogo.getCombinacaoPremiada().isEmpty()) {
                        jogolive.setValue(jogo);
                    } else {
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void setTextoResultado(ArrayList<RadioButton> radioButtons) {



        for (int i = 0; i < radioButtons.size(); i++) {
            StringBuilder textoCartela = new StringBuilder();
            for (String numero : jogo.getCartelas().get(i)) {
                textoCartela.append("\"").append(numero).append("\"").append(" ");
            }
            radioButtons.get(i).setText(textoCartela.toString().trim());

        }

        radiosLive.setValue(radioButtons);

    }

    @SuppressLint("ResourceAsColor")
    public List<Integer> verificarResposta() {


        List<String> resposta = new ArrayList<>();
        List<Integer> numerosAcertados = new ArrayList<>();
        for (int i = 0; i < radiosLive.getValue().size(); i++) {
            if (radiosLive.getValue().get(i).isChecked()) {
                resposta = jogo.getCartelas().get(i);
            }

        }
        if (!resposta.isEmpty()) {
            for (int i = 0; i < resposta.size(); i++) {
                if (jogo.getCombinacaoPremiada().contains(resposta.get(i))) {
                    numerosAcertados.add(1);
                } else {
                    numerosAcertados.add(0);
                }
                textViewsLive.getValue().get(i).setText(resposta.get(i));
                textViewsSorteadosLive.getValue().get(i).setText(jogo.getCombinacaoPremiada().get(i));
            }

        }

        return numerosAcertados;
    }
}