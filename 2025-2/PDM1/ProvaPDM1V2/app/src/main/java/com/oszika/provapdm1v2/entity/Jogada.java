package com.oszika.provapdm1v2.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "jogada",
        foreignKeys = {
                @ForeignKey(entity = Pergunta.class,
                        parentColumns = "pergunta_id",
                        childColumns = "pergunta1_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Pergunta.class,
                        parentColumns = "pergunta_id",
                        childColumns = "pergunta2_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Pergunta.class,
                        parentColumns = "pergunta_id",
                        childColumns = "pergunta3_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Pergunta.class,
                        parentColumns = "pergunta_id",
                        childColumns = "pergunta4_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Pergunta.class,
                        parentColumns = "pergunta_id",
                        childColumns = "pergunta5_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Usuario.class,
                        parentColumns = "usuario_id",
                        childColumns = "usuario_id",
                        onDelete = ForeignKey.CASCADE)
        })
public class Jogada {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "jogada_id")
    public long id;
    @NonNull
    @ColumnInfo(name="usuario_id")
    public long idUsuario;
    @NonNull
    @ColumnInfo(name="pergunta1_id")
    public long idPergunta1;
    @NonNull
    @ColumnInfo(name="pergunta2_id")
    public long idPergunta2;
    @NonNull
    @ColumnInfo(name="pergunta3_id")
    public long idPergunta3;
    @NonNull
    @ColumnInfo(name="pergunta4_id")
    public long idPergunta4;
    @NonNull
    @ColumnInfo(name="pergunta5_id")
    public long idPergunta5;
    @NonNull
    @ColumnInfo(name="pontuacao_pergunta1")
    public double pontuacaoPergunta1;
    @NonNull
    @ColumnInfo(name="pontuacao_pergunta2")
    public double pontuacaoPergunta2;
    @NonNull
    @ColumnInfo(name="pontuacao_pergunta3")
    public double pontuacaoPergunta3;
    @NonNull
    @ColumnInfo(name="pontuacao_pergunta4")
    public double pontuacaoPergunta4;
    @NonNull
    @ColumnInfo(name="pontuacao_pergunta5")
    public double pontuacaoPergunta5;
    @NonNull
    @ColumnInfo(name="pontuacao")
    public double pontuacao;

    public Jogada() {
    }
    @Ignore
    public Jogada(int idUsuario, int idPergunta1, int idPergunta2, int idPergunta3, int idPergunta4, int idPergunta5, double pontuacaoPergunta1, double pontuacaoPergunta2, double pontuacaoPergunta3, double pontuacaoPergunta4, double pontuacaoPergunta5, double pontuacao) {
        this.idUsuario = idUsuario;
        this.idPergunta1 = idPergunta1;
        this.idPergunta2 = idPergunta2;
        this.idPergunta3 = idPergunta3;
        this.idPergunta4 = idPergunta4;
        this.idPergunta5 = idPergunta5;
        this.pontuacaoPergunta1 = pontuacaoPergunta1;
        this.pontuacaoPergunta2 = pontuacaoPergunta2;
        this.pontuacaoPergunta3 = pontuacaoPergunta3;
        this.pontuacaoPergunta4 = pontuacaoPergunta4;
        this.pontuacaoPergunta5 = pontuacaoPergunta5;
        this.pontuacao = pontuacao;
    }
}
