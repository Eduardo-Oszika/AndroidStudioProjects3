package com.oszika.disciplinascomviewmodelenavigationdrawer.ui.quartoPeriodo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.oszika.disciplinascomviewmodelenavigationdrawer.ui.disciplina.Disciplina;
import com.oszika.disciplinascomviewmodelenavigationdrawer.ui.disciplina.DisciplinaRepositorio;

import java.util.List;

public class QuartoPeriodoViewModel extends ViewModel {

    private DisciplinaRepositorio disciplinaRepositorio;
    public MutableLiveData<List<Disciplina>> disciplinas;

    public QuartoPeriodoViewModel() {
        disciplinaRepositorio = new DisciplinaRepositorio();
        disciplinas = new MutableLiveData<>();
        disciplinas.setValue(disciplinaRepositorio.obterDisciplinasPeriodo4());
    }

    public MutableLiveData<List<Disciplina>> getDisciplinas() {
        return disciplinas;
    }
}