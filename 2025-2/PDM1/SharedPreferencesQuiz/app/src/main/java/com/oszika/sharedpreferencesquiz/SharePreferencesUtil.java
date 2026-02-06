package com.oszika.sharedpreferencesquiz;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferencesUtil {
    private static final String PREF_NAME = "dadosUser";
    private static final String KEY_ACERTOS = "acertos";
    private static final String KEY_ERROS = "erros";
    private static final String KEY_LOGADO = "logado";

    public static void salvarLogin(Context context, boolean logado) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit()
                .putBoolean(KEY_LOGADO, logado)
                .apply();
    }

    public static boolean estaLogado(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(KEY_LOGADO, false);
    }

    public static void salvarResultado(Context context, int acertos, int erros) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit()
                .putInt(KEY_ACERTOS, acertos)
                .putInt(KEY_ERROS, erros)
                .apply();
    }

    public static int obterAcertos(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_ACERTOS, 0);
    }

    public static int obterErros(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_ERROS, 0);
    }

    public static boolean contemDados(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.contains(KEY_ACERTOS) && sharedPreferences.contains(KEY_ERROS);
    }

    public static void limparDados(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().clear().apply();
    }
}
