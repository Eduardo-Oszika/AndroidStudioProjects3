package com.oszika.provapdm2v2.ui.entity;

import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "personagem")
public class Personagem {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "personagem_id")
    public Integer id;

    @ColumnInfo(name="personagem_nome")
    public String name;

    @ColumnInfo(name="personagem_imageUrl")
    public String imageUrl;

    public Personagem(){};

    public Personagem(Integer id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    @Ignore
    public  Integer getHabilidade(){
        String[] consoante = new String[]{"B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z"};
        String[] vogal = new String[]{"A", "E", "I", "O", "U"};
        String name = this.name.toUpperCase();
        int habilidade = 0;
        for (int i = 0; i < name.length(); i++) {
            String letter = String.valueOf(name.charAt(i));
            for (String v : vogal) {
                if (letter.equals(v)) {
                    habilidade += 5;
                }
            }
            for (String c : consoante) {
                if (letter.equals(c)) {
                    habilidade += 10;
                }
            }
        }
        Log.d(name, "getHabilidade: " + habilidade);
        return habilidade;
    }
}
