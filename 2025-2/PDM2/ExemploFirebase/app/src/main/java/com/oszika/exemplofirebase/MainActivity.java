package com.oszika.exemplofirebase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UsuarioViewModel usuarioViewModel;
    private UsuarioAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerUsuarios);
        adapter = new UsuarioAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        usuarioViewModel = new ViewModelProvider(this).get(UsuarioViewModel.class);

        usuarioViewModel.getUsuariosLiveData().observe(this, new Observer<List<Usuario>>() {
            @Override
            public void onChanged(List<Usuario> usuarios) {
                adapter = new UsuarioAdapter(usuarios);
                recyclerView.setAdapter(adapter);
            }
        });

        usuarioViewModel.getErroLiveData().observe(this, new Observer<String>() {

            @Override
            public void onChanged(String mensagem) {
                Toast.makeText(MainActivity.this, mensagem, Toast.LENGTH_SHORT).show();
            }
        });

        Button buttonConectar = findViewById(R.id.buttonConectar);
        buttonConectar.setOnClickListener(v -> {
            Usuario novoUsuario = new Usuario(null, "Teste", "teste@gmail.com");
            usuarioViewModel.addUsuario(novoUsuario);

        });
    }
}