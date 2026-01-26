package com.oszika.provapdm2v2.ui.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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
}
