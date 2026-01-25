package com.oszika.provapdm1v2.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "pergunta")
public class Pergunta {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pergunta_id")
    public long perguntaId;

    @NonNull
    @ColumnInfo(name="pergunta_enunciado")
    public String enunciado;

    @NonNull
    @ColumnInfo(name="pergunta_resposta")
    public String Resposta;

    @NonNull
    @ColumnInfo(name="pergunta_dica1")
    public String dica1;

    @NonNull
    @ColumnInfo(name="pergunta_dica2")
    public String dica2;

    @NonNull
    @ColumnInfo(name="pergunta_dica3")
    public String dica3;

    public Pergunta() {
    }

    @Ignore
    public Pergunta( @NonNull String enunciado, @NonNull String resposta, @NonNull String dica1, @NonNull String dica2, @NonNull String dica3) {
        this.enunciado = enunciado;
        Resposta = resposta;
        this.dica1 = dica1;
        this.dica2 = dica2;
        this.dica3 = dica3;
    }
}
