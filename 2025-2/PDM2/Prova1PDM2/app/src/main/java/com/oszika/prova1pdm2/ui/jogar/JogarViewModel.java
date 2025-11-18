package com.oszika.prova1pdm2.ui.jogar;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class JogarViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<String> mTextResposta;
    private final MutableLiveData<Integer> mLayout,mLayout2,mLayout3,mLayout4;
    private List<Integer> numerosAtomicos, numerosSelecionados;

    public JogarViewModel() {
        mText = new MutableLiveData<>();
        mTextResposta = new MutableLiveData<>();
        mLayout = new MutableLiveData<Integer>();
        mLayout2 = new MutableLiveData<Integer>();
        mLayout3 = new MutableLiveData<Integer>();
        mLayout4 = new MutableLiveData<Integer>();
        numerosAtomicos = new ArrayList<Integer>();
        numerosSelecionados = new ArrayList<Integer>();
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<String> getTextResposta() {
        return mTextResposta;
    }

    public LiveData<Integer> getLayout() {
        return mLayout;
    }
    public LiveData<Integer> getLayout2() {
        return mLayout2;
    }
    public LiveData<Integer> getLayout3() {
        return mLayout3;
    }
    public LiveData<Integer> getLayout4() {
        return mLayout4;
    }

    public void setNumerosAtomicos(List<Integer> numerosAtomicos) {
        this.numerosAtomicos = numerosAtomicos;
    }

    public void incrementarNumero(int numero) {
        setNumerosSelecionados(numero);

        Handler handler = new Handler(Looper.getMainLooper());
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < numero; i++) {
                    int index = i;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mText.setValue(String.valueOf(numerosSelecionados.get(index)));
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        setLinearLayout3Visivel();
                    }
                });
            }
        }).start();
    }

    public void setNumerosSelecionados(int numero) {
        Collections.shuffle(numerosAtomicos, new Random());
        numerosSelecionados = numerosAtomicos.subList(0, numero);
    }

    public void setLinearLayout2Visivel() {
        mLayout.setValue(View.GONE);
        mLayout2.setValue(View.VISIBLE);
        mLayout3.setValue(View.GONE);
        mLayout4.setValue(View.GONE);
    }

    public void setLinearLayout3Visivel() {
        mLayout.setValue(View.GONE);
        mLayout2.setValue(View.GONE);
        mLayout3.setValue(View.VISIBLE);
        mLayout4.setValue(View.GONE);
    }

    private void setLinearLayout4Visivel() {
        mLayout.setValue(View.GONE);
        mLayout2.setValue(View.GONE);
        mLayout3.setValue(View.GONE);
        mLayout4.setValue(View.VISIBLE);
    }

    public void verificarResposta(int resposta) {
        setLinearLayout4Visivel();
        int soma = 0;
        for (int numero : numerosSelecionados) {
            soma += numero;
        }
        if (soma == resposta) {
            mTextResposta.setValue("Resposta correta! A soma é: " + soma);
        } else {
            mTextResposta.setValue("Resposta incorreta! A soma correta é: " + soma+ " e você respondeu: " + resposta);
        }
    }
}