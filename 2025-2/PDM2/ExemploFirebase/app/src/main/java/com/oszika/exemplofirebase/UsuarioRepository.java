package com.oszika.exemplofirebase;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private DatabaseReference firebase;

    public UsuarioRepository() {
        firebase = FirebaseDatabase.getInstance().getReference("usuarios");
    }

    public void addUsuario(Usuario usuario){
        String id = firebase.push().getKey();
        usuario.setId(id);

        firebase.child(id).setValue(usuario); //JSON
    }//

    public void obterUsuarios(UsuarioListener listener){
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Usuario> lista = new ArrayList<>();
                for(DataSnapshot child: snapshot.getChildren()){
                    Usuario usuario = child.getValue(Usuario.class);
                    if(usuario!=null){
                        lista.add(usuario);
                    }
                }//for
                listener.onSucess(lista);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onError(error.getMessage());
            }
        });
    }//

    public interface UsuarioListener{
        void onSucess(List<Usuario> usuarios);
        void onError(String error);
    }//
}//class
