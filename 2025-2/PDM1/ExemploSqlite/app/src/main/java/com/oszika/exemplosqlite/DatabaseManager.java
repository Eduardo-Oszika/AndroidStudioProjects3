package com.oszika.exemplosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DatabaseManager {
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long adicionar(Pessoa p) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.NOME, p.getNome());
        values.put(DBHelper.TELEFONE, p.getTelefone());
        return database.insert(DBHelper.BANCO_TABELA,
                null, values);
    }

    public ArrayList<Pessoa> listar() {
        ArrayList<Pessoa> dados = new ArrayList<>();
            String consulta = "SELECT * FROM " + DBHelper.BANCO_TABELA;
            Cursor cursor = database.rawQuery(consulta, null);
            while (cursor.moveToNext()) {
            Pessoa p = new Pessoa();
            p.setId(cursor.getInt(0));
            p.setNome(cursor.getString(1));
            p.setTelefone(cursor.getString(2));
            dados.add(p);
        }
        cursor.close();
        return dados;
    }

    public int atualizar(Pessoa p) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.NOME, p.getNome());
        values.put(DBHelper.TELEFONE, p.getTelefone());
        String where = DBHelper.ID + "=?";
        String[] args = {String.valueOf(p.getId())};
        return database.update(DBHelper.BANCO_TABELA,
                values, where, args);
    }

    public int excluir(Pessoa p) {
        String where = DBHelper.ID + "=?";
        String[] args = {String.valueOf(p.getId())};
        return database.delete(DBHelper.BANCO_TABELA, where, args);
    }
}
