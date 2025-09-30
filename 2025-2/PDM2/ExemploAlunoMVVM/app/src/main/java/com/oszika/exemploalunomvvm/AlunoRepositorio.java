package com.oszika.exemploalunomvvm;

import java.util.ArrayList;
import java.util.List;

public class AlunoRepositorio {
    public List<Aluno> obterAlunos(){
        List<Aluno> listaAlunos = new ArrayList<>();
        listaAlunos.add(new Aluno("Ana", 4));
        listaAlunos.add(new Aluno("Rodrigo", 6));
        listaAlunos.add(new Aluno("Paulo", 10));
        return listaAlunos;
    }
}
