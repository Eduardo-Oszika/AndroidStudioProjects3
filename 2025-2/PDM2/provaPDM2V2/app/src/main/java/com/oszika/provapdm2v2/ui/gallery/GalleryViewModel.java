package com.oszika.provapdm2v2.ui.gallery;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.oszika.provapdm2v2.dao.AppDao;
import com.oszika.provapdm2v2.dao.AppDatabase;
import com.oszika.provapdm2v2.ui.entity.Jogada;
import com.oszika.provapdm2v2.ui.entity.Personagem;

import java.util.ArrayList;

public class GalleryViewModel extends ViewModel {
    private AppDao dao;
    private final MutableLiveData<ArrayList<Personagem>> lista;
    private final MutableLiveData<Jogada> jogadaLive;


    public GalleryViewModel() {
        jogadaLive = new MutableLiveData<>();
        lista = new MutableLiveData<>();

    }

    public void obterBanco(Context context) {
        AppDatabase db = AppDatabase.getDatabase(context.getApplicationContext());
        dao = db.appDao();
    }

    public MutableLiveData<ArrayList<Personagem>> getPersonagens() {
        return lista;
    }

    public MutableLiveData<Jogada> getJogadaLive() {
        return jogadaLive;
    }

    public String gerarJogada(int id, int id2) {
        ArrayList<Personagem> personagens = new ArrayList<>();
        String verificador = verificaridPersonagem(id, id2);
        if (verificador.isEmpty()) {
            personagens.add(dao.obterPersonagemPorId(id));
            personagens.add(dao.obterPersonagemPorId(id2));
            lista.setValue(personagens);
        }
        else {
            return verificador;
        }
        return "";
    }

    private String verificaridPersonagem(int id, int id2) {
        Personagem personagem = dao.obterPersonagemPorId(id);
        Personagem personagem1 = dao.obterPersonagemPorId(id2);
        if (personagem == null) {
            return "Personagem 1 inválido!";
        }
        if (personagem1 == null) {
            return "Personagem 2 inválido!";
        }

        return "";
    }

    public void jogar(String string) {
        String resultado = "";
        Personagem personagem1 = lista.getValue().get(0);
        Personagem personagem2 = lista.getValue().get(1);
        if (personagem1.getHabilidade()> personagem2.getHabilidade())
            resultado = "Jogador venceu!";
        else if (personagem1.getHabilidade()< personagem2.getHabilidade())
            resultado = "Android venceu!";
        else
            resultado = "Empate!";

        Jogada jogada = new Jogada(string,personagem1.id,personagem2.id,resultado);
        Long l = dao.insertJogada(jogada);
        jogadaLive.setValue(dao.obterJogadaPorId(l));

    }
}