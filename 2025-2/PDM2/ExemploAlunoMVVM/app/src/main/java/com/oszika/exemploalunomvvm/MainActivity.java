package com.oszika.exemploalunomvvm;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.oszika.exemploalunomvvm.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AlunoViewModel alunoViewModel;
    private AlunoAdapter adapter;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        alunoViewModel = new ViewModelProvider(this).get(AlunoViewModel.class);
        adapter = new AlunoAdapter(this, new ArrayList<Aluno>());
        binding.listViewAlunos.setAdapter(adapter);

        alunoViewModel.getAlunos().observe(this, new Observer<List<Aluno>>() {
            @Override
            public void onChanged(List<Aluno> alunos) {

                adapter.clear();
                adapter.addAll(alunos);
                adapter.notifyDataSetChanged();
            }
        });

    }
}