package com.oszika.provapdm1v2.service;

import com.oszika.provapdm1v2.MainActivity;
import com.oszika.provapdm1v2.dao.AppDao;
import com.oszika.provapdm1v2.entity.Usuario;

public class ServiceUsuario {
    private final AppDao dao;

    public ServiceUsuario(AppDao dao) {
        this.dao = dao;
    }

    public void insertUsuarioDefaults() {
        Usuario user = dao.autenticarUsuario("root","123");
        if (user == null){
        dao.inserirUsuario(new Usuario("root","123","administrador"));
        }
    }
}
