package com.oszika.exemplojsonlocal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.oszika.exemplojsonlocal.R;
import com.oszika.exemplojsonlocal.entity.Estudante;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNome, editTextDisciplina, editTextNota;
    private List<Estudante> lista;
    private TextView textViewResultado;
    private String retorno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextNome = findViewById(R.id.editTextNome);
        editTextDisciplina = findViewById(R.id.editTextDisciplina);
        editTextNota = findViewById(R.id.editTextNota);
        textViewResultado = findViewById(R.id.textViewResultado);
        lista = new ArrayList<>();
    }

    public void adicionarEstudante(View v) {
        try {
            int nota = Integer.parseInt(editTextNota.getText().toString());
            lista.add(new Estudante(
                    editTextNome.getText().toString(),
                    editTextDisciplina.getText().toString(),
                    nota
            ));
            Toast.makeText(getApplicationContext(), "Item inserido", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Digite uma nota válida", Toast.LENGTH_SHORT).show();
        }
    }

    public String criarJson() {
        JSONArray jsonArray = new JSONArray();
        for (Estudante estudante : lista) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("nomeEstudante", estudante.getNome());
                jsonObject.put("disciplinaEstudante", estudante.getDisciplina());
                jsonObject.put("notaEstudante", estudante.getNota());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return "{\"estudantes\":" + jsonArray.toString() + "}";
    }

    public void gerarJson(View v) {
        retorno = criarJson();
        textViewResultado.setText(retorno);
    }

    public void abrirTela(View v) {
        if (retorno == null || retorno.isEmpty()) {
            Toast.makeText(this, "Nenhum dado gerado", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(getApplicationContext(), Activity2.class);
        intent.putExtra("dados", retorno);
        startActivity(intent);
    }
}