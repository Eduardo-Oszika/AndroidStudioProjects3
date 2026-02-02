package com.oszika.provapdm1v2.service;

import android.content.Context;

import com.oszika.provapdm1v2.dao.AppDao;
import com.oszika.provapdm1v2.dao.AppDatabase;
import com.oszika.provapdm1v2.entity.Pergunta;

import java.util.List;

public class ServicePergunta {
    private final AppDao dao;

    public ServicePergunta(Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        this.dao = db.appDao();
    }

    public void insertPerguntaDefaults() {
        List<Pergunta> perguntas = dao.obterPerguntas();
        if (perguntas.size() < 5) {
            dao.inserirPergunta(new Pergunta("Qual é o principal objetivo de Gon Freecss ao se tornar um Hunter?", "Encontrar seu pai, Ging Freecss", "Está relacionado à família", "O pai abandonou o filho ainda pequeno", "Ser Hunter facilita buscas pelo mundo"));
            dao.inserirPergunta(new Pergunta("Qual tipo de Nen é característico de usuários que conseguem fortalecer diretamente o próprio corpo?", "Reforço", "Muito usado em combate físico", "Aumenta força e resistência", "É o tipo de Nen de Gon"));
            dao.inserirPergunta(new Pergunta("Quem é o assassino profissional da família Zoldyck que se torna amigo de Gon?", "Killua Zoldyck", "Usa eletricidade", "Vem de uma família extremamente perigosa", "Tem quase a mesma idade de Gon"));
            dao.inserirPergunta(new Pergunta("Qual é o nome da técnica especial de Nen criada por Kurapika para combater a Trupe Fantasma?", "Correntes do Julgamento", "Envolve correntes", "Só pode ser usada contra membros específicos", "É mortal para a Trupe Fantasma"));
            dao.inserirPergunta(new Pergunta("Qual é o nome do jogo mortal que mistura Nen e videogame no arco dos Hunters?", "Greed Island", "Funciona como um jogo real", "Usa cartas especiais", "Acontece em uma ilha isolada"));
        }
    }

    public List<Pergunta> obterPerguntas() {
        return dao.obterPerguntas();
    }

    public Long inserirPergunta(Pergunta pergunta) {
        return dao.inserirPergunta(pergunta);
    }
}
