package com.oszika.exemplosharepreferences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SegundaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private TextView textViewNome;
    private ListView listViewCores;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_segunda);

        textViewNome = findViewById(R.id.tvNome);
        listViewCores = findViewById(R.id.lvCores);

        Intent intent = getIntent();
        String nome = intent.getStringExtra("nome");
        textViewNome.setText(nome);

        String[] cores = getResources().getStringArray(R.array.cores_array);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cores);
        listViewCores.setAdapter(adapter);
        listViewCores.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String corSelecionada = (String) adapterView.getItemAtPosition(i);
        gravar(corSelecionada);
    }

    private void gravar(String corSelecionada) {
        String nome = textViewNome.getText().toString();
        boolean sucesso = SharePreferencesUtil.salvarDados(this, nome, corSelecionada);
        if (sucesso) {
            Intent it = new Intent(this, TerceiraActivity.class);
            startActivity(it);
        } else
            Toast.makeText(this, "Dados nao salvos", Toast.LENGTH_SHORT).show();

    }
}