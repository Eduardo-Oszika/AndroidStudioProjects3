package com.oszika.disciplinascomviewmodelenavigationdrawer.ui.sextoPeriodo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.oszika.disciplinascomviewmodelenavigationdrawer.ui.disciplina.Disciplina;
import com.oszika.disciplinascomviewmodelenavigationdrawer.ui.disciplina.DisciplinaRepositorio;

import java.util.List;

public class SextoPeriodoViewModel extends ViewModel {

    private DisciplinaRepositorio disciplinaRepositorio;
    public MutableLiveData<List<Disciplina>> disciplinas;

    public SextoPeriodoViewModel() {
        disciplinaRepositorio = new DisciplinaRepositorio();
        disciplinas = new MutableLiveData<>();
        disciplinas.setValue(disciplinaRepositorio.obterDisciplinasPeriodo6());
    }

    public MutableLiveData<List<Disciplina>> getDisciplinas() {
        return disciplinas;
    }
}