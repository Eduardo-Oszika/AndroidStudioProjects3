package com.oszika.provapdm1v2.service;

import android.content.Context;
import android.database.Cursor;

import com.oszika.provapdm1v2.dao.AppDao;
import com.oszika.provapdm1v2.dao.AppDatabase;
import com.oszika.provapdm1v2.entity.Jogada;
import com.oszika.provapdm1v2.entity.Pergunta;

import java.util.ArrayList;
import java.util.List;

public class ServiceJogadas {
    private final AppDao dao;

    public ServiceJogadas(Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        this.dao = db.appDao();
    }

    public Cursor obterPerguntaPorID(long perguntaId) {
        return dao.obterPerguntaPorID(perguntaId);
    }

    public List<Pergunta> obterPerguntas(Jogada jogada) {
        List<Pergunta> perguntas = new ArrayList<>();
        Cursor cursor = dao.obterPerguntaPorID(jogada.idPergunta1);
        perguntas.add(addPergunta(cursor));
        cursor = dao.obterPerguntaPorID(jogada.idPergunta2);
        perguntas.add(addPergunta(cursor));
        cursor = dao.obterPerguntaPorID(jogada.idPergunta3);
        perguntas.add(addPergunta(cursor));
        cursor = dao.obterPerguntaPorID(jogada.idPergunta4);
        perguntas.add(addPergunta(cursor));
        cursor = dao.obterPerguntaPorID(jogada.idPergunta5);
        perguntas.add(addPergunta(cursor));

        return perguntas;
    }

    private static Pergunta addPergunta(Cursor cursor) {
        if (cursor != null && cursor.moveToFirst()) {
            Pergunta p = new Pergunta();
            p.perguntaId = cursor.getLong(cursor.getColumnIndexOrThrow("pergunta_id"));
            p.enunciado = cursor.getString(cursor.getColumnIndexOrThrow("pergunta_enunciado"));
            p.Resposta = cursor.getString(cursor.getColumnIndexOrThrow("pergunta_resposta"));
            p.dica1 = cursor.getString(cursor.getColumnIndexOrThrow("pergunta_dica1"));
            p.dica2 = cursor.getString(cursor.getColumnIndexOrThrow("pergunta_dica2"));
            p.dica3 = cursor.getString(cursor.getColumnIndexOrThrow("pergunta_dica3"));

            return p;
        }
        return null;
    }

    public Jogada getUltimaJogada() {
        Cursor cursor = dao.obterUltimaJogada();
        if (cursor != null && cursor.moveToFirst()) {
            Jogada jogada = new Jogada();
            jogada.id = cursor.getInt(cursor.getColumnIndexOrThrow("jogada_id"));
            jogada.idUsuario = cursor.getInt(cursor.getColumnIndexOrThrow("usuario_id"));
            jogada.idPergunta1 = cursor.getInt(cursor.getColumnIndexOrThrow("pergunta1_id"));
            jogada.idPergunta2 = cursor.getInt(cursor.getColumnIndexOrThrow("pergunta2_id"));
            jogada.idPergunta3 = cursor.getInt(cursor.getColumnIndexOrThrow("pergunta3_id"));
            jogada.idPergunta4 = cursor.getInt(cursor.getColumnIndexOrThrow("pergunta4_id"));
            jogada.idPergunta5 = cursor.getInt(cursor.getColumnIndexOrThrow("pergunta5_id"));
            jogada.pontuacao = cursor.getDouble(cursor.getColumnIndexOrThrow("pontuacao"));
            jogada.pontuacaoPergunta1 = cursor.getInt(cursor.getColumnIndexOrThrow("pontuacao_pergunta1"));
            jogada.pontuacaoPergunta2 = cursor.getInt(cursor.getColumnIndexOrThrow("pontuacao_pergunta2"));
            jogada.pontuacaoPergunta3 = cursor.getInt(cursor.getColumnIndexOrThrow("pontuacao_pergunta3"));
            jogada.pontuacaoPergunta4 = cursor.getInt(cursor.getColumnIndexOrThrow("pontuacao_pergunta4"));
            jogada.pontuacaoPergunta5 = cursor.getInt(cursor.getColumnIndexOrThrow("pontuacao_pergunta5"));
            return jogada;
        } else {
            return null;
        }
    }

    public Long salvarJogada(Jogada jogada) {
        return dao.inserirJogada(jogada);
    }
}
