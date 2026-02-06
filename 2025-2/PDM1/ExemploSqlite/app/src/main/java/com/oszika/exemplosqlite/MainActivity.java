package com.oszika.exemplosqlite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNome, editTextTelefone;
    private Button buttonS, buttonD, buttonL;
    private Pessoa p;
    private DatabaseManager databaseManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        editTextNome = findViewById(R.id.editTextNome);
        editTextTelefone = findViewById(R.id.editTextTelefone);
        buttonS = findViewById(R.id.buttonS);
        buttonL = findViewById(R.id.buttonL);
        buttonD = findViewById(R.id.buttonD);

        databaseManager = new DatabaseManager(this);
        databaseManager.open();

        p = getIntent().getParcelableExtra("dado");
        if(p != null){
            editTextNome.setText(p.getNome());
            editTextTelefone.setText(p.getTelefone());
        }
    }

    public void clicar(View view) {
        if (view.getId() == R.id.buttonS ) {
            if (p == null) {
                p = new Pessoa();
                p.setNome(editTextNome.getText().toString());
                p.setTelefone(editTextTelefone.getText().toString());
                long retorno = databaseManager.adicionar(p);
                if (retorno != -1) {
                    Toast.makeText(MainActivity.this, "Salvo",
                            Toast.LENGTH_SHORT).show();
                    p = null;
                    editTextNome.setText("");
                    editTextTelefone.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao salvar",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                p.setNome(editTextNome.getText().toString());
                p.setTelefone(editTextTelefone.getText().toString());
                int retorno = databaseManager.atualizar(p);
                if (retorno != 0) {
                    Toast.makeText(MainActivity.this, "Atualizado",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao atualizar",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }

        if (view.getId() == R.id.buttonL ) {
            ArrayList<Pessoa> dados = databaseManager.listar();
            Log.i("dados", dados.toString());
            if (dados != null && !dados.isEmpty()) {
                Intent it = new Intent(MainActivity.this,
                        SegundaActivity.class);
                it.putParcelableArrayListExtra("dados", dados);
                startActivity(it);
            } else {
                Toast.makeText(MainActivity.this, "Sem dados",
                        Toast.LENGTH_SHORT).show();
            }
        }

        if (view.getId() == R.id.buttonD) {
            if (p != null) {
                int retorno = databaseManager.excluir(p);
                if (retorno > 0) {
                    Toast.makeText(MainActivity.this, "Dados deletados",
                            Toast.LENGTH_SHORT).show();
                    editTextNome.setText("");
                    editTextTelefone.setText("");
                    p = null;
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao deletar",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Nenhum registro selecionado",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseManager.close();
    }
}
