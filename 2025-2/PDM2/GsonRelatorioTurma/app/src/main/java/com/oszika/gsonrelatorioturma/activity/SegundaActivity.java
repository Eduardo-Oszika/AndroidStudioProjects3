package com.oszika.gsonrelatorioturma.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.oszika.gsonrelatorioturma.R;
import com.oszika.gsonrelatorioturma.entity.Aluno;
import com.oszika.gsonrelatorioturma.entity.Turma;

public class SegundaActivity extends AppCompatActivity {
    private ListView listView;
    private TextView textViewMediaIdade;
    private ArrayAdapter<Aluno> adapterAluno;
    private Turma turma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_segunda);


        listView = findViewById(R.id.lvAprovados);
        textViewMediaIdade = findViewById(R.id.tvMediaIdades);


        turma = getIntent().getSerializableExtra("turma", Turma.class);
        adapterAluno = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, turma.getAprovados());
        listView.setAdapter(adapterAluno);

        textViewMediaIdade.setText(String.format("Média das idades: %.2f", turma.mediaIdade()));
    }
}