package com.oszika.provapdm1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.oszika.provapdm1.entity.Elementos;
import com.oszika.provapdm1.entity.Usuario;

public class ListaElementosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;
    ArrayAdapter<String> adapter;
    String[] elementos;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_elementos);

        elementos = getResources().getStringArray(R.array.elementosNome);
        listView = findViewById(R.id.lv_elementos);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, elementos);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(ListaElementosActivity.this);
        usuario = getIntent().getSerializableExtra("user", Usuario.class);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, String.valueOf(i), Toast.LENGTH_SHORT).show();

        Intent it = new Intent(this, ElementoActivity.class);
//        Elementos elementoEnum = getEnum(i);
        Elementos elementoEnum = Elementos.values()[i];
        it.putExtra("elementoENUM", elementoEnum);
        startActivity(it);
    }

//    private Elementos getEnum(int i) {
//        if (i==0)
//            return Elementos.hidrogenio;
//        if (i==1)
//            return Elementos.litio;
//        if (i==2)
//            return Elementos.sodio;
//        if (i==3)
//            return Elementos.potassio;
//        if (i==4)
//            return Elementos.rubidio;
//        if (i==5)
//            return Elementos.cesio;
//        if (i==6)
//            return Elementos.francio;
//        if (i==7)
//            return Elementos.cobre;
//        if (i==8)
//            return Elementos.prata;
//        if (i==9)
//            return Elementos.ouro;
//        if (i==10)
//            return Elementos.roentgenio;
//        if (i==11)
//            return Elementos.titanio;
//        return null;
//    }

    public void jogar(View view) {
        Intent it = new Intent(this, JogarActivity.class);
        if (usuario != null) {
            it.putExtra("user", usuario);
        }
        startActivity(it);
    }
}