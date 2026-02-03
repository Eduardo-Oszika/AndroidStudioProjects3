package com.oszika.provapdm2v2.ui.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "jogada",
        foreignKeys = {
                @ForeignKey(entity = Personagem.class,
                        parentColumns = "personagem_id",
                        childColumns = "jogador_id",
                        onDelete = ForeignKey.SET_NULL),
                @ForeignKey(entity = Personagem.class,
                        parentColumns = "personagem_id",
                        childColumns = "android_id",
                        onDelete = ForeignKey.SET_NULL)
        }
)
public class Jogada {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "jogada_id")
    public Integer id;
    @ColumnInfo(name = "jogada_nome")
    public String nome;
    @ColumnInfo(name = "jogador_id")
    public Integer jogadorId;
    @ColumnInfo(name = "android_id")
    public Integer androidId;
    @ColumnInfo(name = "resultado")
    public String resultado;

    public Jogada() {
    }

    @Ignore
    public Jogada(Integer id, String nome, Integer jogadorId, Integer androidId) {
        this.id = id;
        this.nome = nome;
        this.jogadorId = jogadorId;
        this.androidId = androidId;
    }

    @Ignore
    public Jogada(String nome, Integer jogadorId, Integer androidId, String resultado) {
        this.nome = nome;
        this.jogadorId = jogadorId;
        this.androidId = androidId;
        this.resultado = resultado;
    }
}

