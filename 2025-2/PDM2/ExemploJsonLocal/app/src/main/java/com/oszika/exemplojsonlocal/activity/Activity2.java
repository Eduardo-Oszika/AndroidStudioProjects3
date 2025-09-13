package com.oszika.exemplojsonlocal.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.oszika.exemplojsonlocal.R;
import com.oszika.exemplojsonlocal.entity.Estudante;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Activity2 extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private String dadosJSON;
    private ListView listView;
    private List<Estudante> lista;
    private ArrayAdapter<Estudante> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        dadosJSON = getIntent().getStringExtra("dados");
        listView = findViewById(R.id.listViewDados);
        lista = consumirJSON();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Dados Estudante")
                .setMessage("Nome: " + lista.get(position).getNome()
                        + "\nDisciplina: " + lista.get(position).getDisciplina()
                        + "\nNota: " + lista.get(position).getNota())
                .setPositiveButton("OK", null)
                .create();
        alertDialog.show();
    }

    private List<Estudante> consumirJSON() {
        List<Estudante> listaEstudantes = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(dadosJSON);
            JSONArray jsonArray = jsonObject.getJSONArray("estudantes");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                Estudante estudante = new Estudante();
                estudante.setNome(object.getString("nomeEstudante"));
                estudante.setDisciplina(object.getString("disciplinaEstudante"));
                estudante.setNota(object.getInt("notaEstudante"));
                listaEstudantes.add(estudante);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listaEstudantes;
    }
}