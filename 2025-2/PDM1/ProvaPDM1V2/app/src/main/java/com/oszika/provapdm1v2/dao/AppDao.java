package com.oszika.provapdm1v2.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.oszika.provapdm1v2.entity.Jogada;
import com.oszika.provapdm1v2.entity.Pergunta;
import com.oszika.provapdm1v2.entity.Usuario;

import java.util.List;

@Dao
public interface AppDao {
    @Insert
    Long inserirUsuario(Usuario c);

    @Insert
    Long inserirPergunta(Pergunta p);

    @Insert
    Long inserirJogada(Jogada jogada);

    @Query("SELECT * FROM usuario")
    List<Usuario> obterUsuario();

    @Query("SELECT * FROM pergunta")
    List<Pergunta> obterPerguntas();

    @Query("SELECT * FROM usuario WHERE usuario_name = :username AND usuario_senha = :password LIMIT 1")
    Usuario autenticarUsuario(String username, String password);

    @Query("SELECT * FROM pergunta WHERE pergunta_id = :perguntaId LIMIT 1")
    Cursor obterPerguntaPorID(long perguntaId);

    @Query("SELECT * FROM jogada ORDER BY jogada_id DESC LIMIT 1")
    Cursor obterUltimaJogada();
}
