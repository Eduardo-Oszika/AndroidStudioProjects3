package com.oszika.exemplosharepreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferencesUtil {
    private static final String PREF_NAME = "dadosUser";
    private static final String KEY_NOME = "nome";
    private static final String KEY_COR = "cor";

    public static boolean salvarDados(Context context, String nome, String cor) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NOME, nome);
        editor.putString(KEY_COR, cor);
        return editor.commit();
    }

    public static String obterNomeUsuario(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NOME, "Nenhum nome encontrado");
    }

    public static String obterCorFundo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_COR, "Branco");
    }

    public static boolean contemDados(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.contains(KEY_NOME) && sharedPreferences.contains(KEY_COR);
    }
}
