package com.oszika.provapdm1v2.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "usuario")
public class Usuario {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "usuario_id")
    public long usuarioId;

    @NonNull
    @ColumnInfo(name="usuario_name")
    public String usuarioname;


    @NonNull
    @ColumnInfo(name="usuario_senha")
    public String senha;

    @NonNull
    @ColumnInfo(name="usuario_tipo")
    public String tipo;

    public Usuario() {
    }

    @Ignore
    public Usuario( @NonNull String usuarioname, @NonNull String senha, @NonNull String tipo) {
        this.usuarioname = usuarioname;
        this.senha = senha;
        this.tipo = tipo;
    }

    public boolean isAdministrador() {
        return "administrador".equalsIgnoreCase(this.tipo);
    }
}
