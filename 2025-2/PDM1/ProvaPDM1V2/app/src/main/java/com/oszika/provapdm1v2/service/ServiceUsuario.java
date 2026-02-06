package com.oszika.provapdm1v2.service;

import android.content.Context;

import com.oszika.provapdm1v2.dao.AppDao;
import com.oszika.provapdm1v2.dao.AppDatabase;
import com.oszika.provapdm1v2.entity.Usuario;

public class ServiceUsuario {
    private final AppDao dao;

    public ServiceUsuario(Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        dao = db.appDao();
    }

    public void insertUsuarioDefaults() {
        Usuario user = dao.autenticarUsuario("root","123");
        if (user == null){
        dao.inserirUsuario(new Usuario("root","123","administrador"));
        }
    }

    public Usuario autenticarUsuario(String username, String password) {
        return dao.autenticarUsuario(username, password);
    }

    public void inserirUsuario(Usuario usuario) {
        dao.inserirUsuario(usuario);
    }
}
