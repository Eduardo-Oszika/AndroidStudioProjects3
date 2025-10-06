package com.oszika.disciplinascomviewmodelenavigationdrawer.ui.segundoPeriodo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.oszika.disciplinascomviewmodelenavigationdrawer.ui.disciplina.Disciplina;
import com.oszika.disciplinascomviewmodelenavigationdrawer.ui.disciplina.DisciplinaRepositorio;

import java.util.List;

public class SegundoPeriodoViewModel extends ViewModel {

    private DisciplinaRepositorio disciplinaRepositorio;
    public MutableLiveData<List<Disciplina>> disciplinas;

    public SegundoPeriodoViewModel() {
        disciplinaRepositorio = new DisciplinaRepositorio();
        disciplinas = new MutableLiveData<>();
        disciplinas.setValue(disciplinaRepositorio.obterDisciplinasPeriodo2());
    }

    public MutableLiveData<List<Disciplina>> getDisciplinas() {
        return disciplinas;
    }
}