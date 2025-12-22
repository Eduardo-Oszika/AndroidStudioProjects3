package com.oszika.planetalist.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_planet")
public class UserPlanet {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_planet_id")
    public Long userPlanetId;

    @NonNull
    @ColumnInfo(name = "user_planet_nome")
    public String nome;

    @NonNull
    @ColumnInfo(name = "user_planet_massa")
    public String massa;

    @NonNull
    @ColumnInfo(name = "user_planet_planeta_nome")
    public String planetaNome;

    @NonNull
    @ColumnInfo(name = "user_planet_peso_na_superficie")
    public Double pesoNaSuperficie;

    public UserPlanet() {
    }

    @Ignore
    public UserPlanet(@NonNull String nome, @NonNull String massa, @NonNull String planetaNome, @NonNull Double pesoNaSuperficie) {
        this.nome = nome;
        this.massa = massa;
        this.planetaNome = planetaNome;
        this.pesoNaSuperficie = pesoNaSuperficie;
    }

}
