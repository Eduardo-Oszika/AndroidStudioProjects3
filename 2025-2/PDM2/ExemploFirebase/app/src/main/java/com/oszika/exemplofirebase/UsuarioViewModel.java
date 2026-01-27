package com.oszika.exemplofirebase;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class UsuarioViewModel extends ViewModel {
    private UsuarioRepository repository;
    private MutableLiveData<List<Usuario>> usuariosLiveData;
    private MutableLiveData<String> erroLiveData;

    public UsuarioViewModel() {
        repository = new UsuarioRepository();
        usuariosLiveData = new MutableLiveData<>();
        erroLiveData = new MutableLiveData<>();

        obterUsuarios();
    }//

    public MutableLiveData<List<Usuario>> getUsuariosLiveData() {
        return usuariosLiveData;
    }

    public MutableLiveData<String> getErroLiveData() {
        return erroLiveData;
    }

    public void obterUsuarios() {
        repository.obterUsuarios(new UsuarioRepository.UsuarioListener() {
            @Override
            public void onSucess(List<Usuario> usuarios) {
                usuariosLiveData.setValue(usuarios);
            }

            @Override
            public void onError(String error) {
                erroLiveData.setValue(error);
            }
        });
    }//

    public void addUsuario(Usuario usuario){
        repository.addUsuario(usuario);
    }//
}//class