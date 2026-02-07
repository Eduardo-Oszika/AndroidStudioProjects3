package com.oszika.exemplosqlite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class SegundaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lista;
    private ArrayAdapter<Pessoa> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_segunda);
        lista = findViewById(R.id.lista);

        ArrayList<Pessoa> dados = getIntent().getParcelableArrayListExtra("dados");
        Log.i("dados2", String.valueOf(dados));

        if(dados != null && !dados.isEmpty()){
            adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, dados);
            lista.setAdapter(adapter);
            lista.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {
        Pessoa p = (Pessoa) parent.getItemAtPosition(position);
        Intent it = new Intent(SegundaActivity.this,
                MainActivity.class);
        it.putExtra("dado", p);
        startActivity(it);
        finish();
    }
}

