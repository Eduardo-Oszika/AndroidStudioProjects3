package com.oszika.exemplosqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;


public class DBHelper  extends SQLiteOpenHelper {
    public static final int BANCO_VERSAO = 1;
    public static final String BANCO_NOME = "banco.db";
    public static final String BANCO_TABELA = "Contato";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String TELEFONE = "telefone";

    private static final String CRIA_TABELA =
            "CREATE TABLE " + BANCO_TABELA +
                    "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NOME + " TEXT, " + TELEFONE + " TEXT" + ")";

    private static final String DELETA_TABELA = "DROP TABLE IF EXISTS " +
            BANCO_TABELA;

    public DBHelper(@Nullable Context context) {
        super(context, BANCO_NOME, null, BANCO_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CRIA_TABELA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DELETA_TABELA);
        onCreate(sqLiteDatabase);
    }
}

