package com.oszika.revisaoprovapdm2.ui.home;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {


    private final MutableLiveData<Boolean> loginSucesso;
    private final MutableLiveData<String> erro;
    private final MutableLiveData<Boolean> cadastroSucesso;

    private static final String AUTHORITY = "com.oszika.revisaoprovapdm2.provider";
    private static final String PATH_PROFESSOR = "professor";
    private static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + PATH_PROFESSOR);


    public HomeViewModel() {
        loginSucesso = new MutableLiveData<>();
        erro = new MutableLiveData<>();
        cadastroSucesso = new MutableLiveData<>();
    }

    public void login(Context context, String username, String senha) {

        Cursor cursor = context.getContentResolver().query(
                CONTENT_URI,
                null,
                "professor_username = ? AND professor_senha = ?",
                new String[]{username, senha},
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            loginSucesso.setValue(true);
            cursor.close();
        } else {
            erro.setValue("Usuário ou senha inválidos");
        }
    }

    public void cadastro(Context context, String username, String senha) {

        if (username.isEmpty() || senha.isEmpty()) {
            erro.setValue("Preencha todos os campos");
            return;
        }

        ContentValues values = new ContentValues();
        values.put("professor_username", username);
        values.put("professor_senha", senha);

        Uri uri = context.getContentResolver().insert(CONTENT_URI, values);

        if (uri != null) {
            cadastroSucesso.setValue(true);
        } else {
            erro.setValue("Erro ao cadastrar usuário");
        }
    }

    public LiveData<Boolean> getLoginSucesso() {
        return loginSucesso;
    }

    public LiveData<String> getErro() {
        return erro;
    }

    public LiveData<Boolean> getCadastroSucesso() {
        return cadastroSucesso;
    }
}