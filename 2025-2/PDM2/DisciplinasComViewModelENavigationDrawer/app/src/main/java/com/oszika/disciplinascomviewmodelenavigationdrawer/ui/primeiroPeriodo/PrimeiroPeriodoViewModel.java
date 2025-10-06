package com.oszika.disciplinascomviewmodelenavigationdrawer.ui.primeiroPeriodo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.oszika.disciplinascomviewmodelenavigationdrawer.ui.disciplina.Disciplina;
import com.oszika.disciplinascomviewmodelenavigationdrawer.ui.disciplina.DisciplinaRepositorio;

import java.util.List;

public class PrimeiroPeriodoViewModel extends ViewModel{

    private DisciplinaRepositorio disciplinaRepositorio;
    public MutableLiveData<List<Disciplina>> disciplinas;

    public PrimeiroPeriodoViewModel() {

        disciplinaRepositorio = new DisciplinaRepositorio();
        disciplinas = new MutableLiveData<>();
        disciplinas.setValue(disciplinaRepositorio.obterDisciplinasPeriodo1());
    }

    public MutableLiveData<List<Disciplina>> getDisciplinas() {
        return disciplinas;
    }

}