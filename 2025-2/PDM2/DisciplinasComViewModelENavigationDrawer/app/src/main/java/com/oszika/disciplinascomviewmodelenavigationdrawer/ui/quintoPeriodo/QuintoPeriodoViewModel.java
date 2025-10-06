package com.oszika.disciplinascomviewmodelenavigationdrawer.ui.terceiroPeriodo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.oszika.disciplinascomviewmodelenavigationdrawer.ui.disciplina.Disciplina;
import com.oszika.disciplinascomviewmodelenavigationdrawer.ui.disciplina.DisciplinaRepositorio;

import java.util.List;

public class QuintoPeriodoViewModel extends ViewModel {

    private DisciplinaRepositorio disciplinaRepositorio;
    public MutableLiveData<List<Disciplina>> disciplinas;

    public QuintoPeriodoViewModel() {
        disciplinaRepositorio = new DisciplinaRepositorio();
        disciplinas = new MutableLiveData<>();
        disciplinas.setValue(disciplinaRepositorio.obterDisciplinasPeriodo5());
    }

    public MutableLiveData<List<Disciplina>> getDisciplinas() {
        return disciplinas;
    }
}