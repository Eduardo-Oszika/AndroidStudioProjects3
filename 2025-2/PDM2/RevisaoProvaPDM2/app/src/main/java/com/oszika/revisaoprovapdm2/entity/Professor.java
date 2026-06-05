package com.oszika.revisaoprovapdm2.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "professor")
public class Professor {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "professor_id")
    public Long id;

    @ColumnInfo(name = "professor_username")
    public String username;

    @ColumnInfo(name = "professor_senha")
    public String senha;

    public Professor() {
    }

    @Ignore
    public Professor( String username, String senha) {

        this.username = username;
        this.senha = senha;
    }

    @Ignore
    public Professor(Long id, String username, String senha) {
        this.id = id;
        this.username = username;
        this.senha = senha;
    }


}