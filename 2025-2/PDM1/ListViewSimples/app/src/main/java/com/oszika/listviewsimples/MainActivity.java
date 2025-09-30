package com.oszika.listviewsimples;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener {
    private ListView listViewLinguagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listViewLinguagens = findViewById(R.id.lvLinguagens);
        ArrayList<String> linguagens = Repository.getLinguagens();

        String[] linguagensArray = getResources().getStringArray(R.array.linguagens_prog);

//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, linguagens);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, linguagensArray);
        listViewLinguagens.setAdapter(adapter);
        listViewLinguagens.setOnItemClickListener(MainActivity.this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        String linguagem = parent.getItemAtPosition(i).toString();
        Snackbar.make(view, "Linguagem selecionada: " + linguagem, Snackbar.LENGTH_SHORT).show();
    }
}