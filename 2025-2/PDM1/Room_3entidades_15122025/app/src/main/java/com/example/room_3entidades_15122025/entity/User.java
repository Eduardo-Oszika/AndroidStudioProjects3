package com.example.room_3entidades_15122025.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "user",
        indices = {
                @Index(value = {"user_nome"}, unique = true),
        }
)
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    public long userId;

    @ColumnInfo(name = "user_nome")
    public String userNome;

    @ColumnInfo(name = "user_senha")
    public String userSenha;

    public User() {
    }

    @Ignore
    public User(String userNome, String userSenha) {
        this.userNome = userNome;
        this.userSenha = userSenha;
    }
}//class
