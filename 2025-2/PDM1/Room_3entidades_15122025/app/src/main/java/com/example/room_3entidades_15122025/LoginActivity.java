package com.example.room_3entidades_15122025;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.room_3entidades_15122025.entity.User;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private EditText etName, etSenha;
    private Button btnLogin, btnCadastro;
    private AppDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        AppDatabase db = InstanciaDB.getInstance(getApplicationContext());
        Log.d("TAG", "onCreate: "+db.toString());
        dao = db.appDao();
        etName = findViewById(R.id.et_usuario);
        etSenha = findViewById(R.id.et_senha);
        btnLogin = findViewById(R.id.btnLogin);
        btnCadastro = findViewById(R.id.btnCadastrar);

        btnLogin.setOnClickListener(v -> {
            if (verificaCampos()) {

                String name = etName.getText().toString();
                String senha = etSenha.getText().toString();
                User userAutenticado = dao.autenticar(name, senha);
                if (!Objects.isNull(userAutenticado)) {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Usuario ou senha incorreto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCadastro.setOnClickListener(v -> {
            if (verificaCampos()) {
                String name = etName.getText().toString();
                String senha = etSenha.getText().toString();

                Long id = dao.inserirUser(new User(name, senha));
                if (id > 0) {
                    Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Erro ao cadastrar usuário! Já existe um usuário com esse nome.", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private boolean verificaCampos() {
        if (etName.getText().toString().isEmpty()) {
            etName.setError("Campo obrigatório");
            return false;
        }
        if (etSenha.getText().toString().isEmpty()) {
            etSenha.setError("Campo obrigatório");
            return false;
        }
        return true;
    }


}