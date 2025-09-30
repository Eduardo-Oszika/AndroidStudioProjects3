package com.oszika.exemploalunomvvm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class AlunoViewModel extends ViewModel {
    private AlunoRepositorio alunoRepositorio;
    public MutableLiveData<List<Aluno>> alunos;

    public AlunoViewModel() {
        alunoRepositorio = new AlunoRepositorio();
        alunos = new MutableLiveData<>();
        alunos.setValue(alunoRepositorio.obterAlunos());
    }

    public MutableLiveData<List<Aluno>> getAlunos() {
        return alunos;
    }
}
