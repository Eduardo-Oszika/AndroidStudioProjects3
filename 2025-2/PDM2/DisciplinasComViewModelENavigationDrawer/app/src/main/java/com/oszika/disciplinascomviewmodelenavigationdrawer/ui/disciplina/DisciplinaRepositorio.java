package com.oszika.disciplinascomviewmodelenavigationdrawer.ui.disciplina;

import com.oszika.disciplinascomviewmodelenavigationdrawer.R;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaRepositorio {
    public List<Disciplina> obterDisciplinasPeriodo1(){
        List<Disciplina> listaDisciplinas =  new ArrayList<>();
        listaDisciplinas.add(new Disciplina("Informática Instrumental", R.drawable.smile));
        listaDisciplinas.add(new Disciplina("Inglês Técnico", R.drawable.sad));
        listaDisciplinas.add(new Disciplina("Introdução À Computação", R.drawable.smile));
        listaDisciplinas.add(new Disciplina("Introdução à Lógica", R.drawable.smile));
        listaDisciplinas.add(new Disciplina("Tendências Tecnológicas para o Mercado de TI", R.drawable.neutral));
        return listaDisciplinas;
    }

    public List<Disciplina> obterDisciplinasPeriodo2(){
        List<Disciplina> listaDisciplinas =  new ArrayList<>();
        listaDisciplinas.add(new Disciplina("Análise de Sistemas", R.drawable.smile));
        listaDisciplinas.add(new Disciplina("Fundamentos e Projeto de Banco de Dados", R.drawable.neutral));
        listaDisciplinas.add(new Disciplina("Introdução à Programação", R.drawable.smile));
        listaDisciplinas.add(new Disciplina("Programação Básica para Web", R.drawable.smile));
        listaDisciplinas.add(new Disciplina("Programação Orientada À Objetos", R.drawable.smile));
        return listaDisciplinas;
    }

    public List<Disciplina> obterDisciplinasPeriodo3(){
        List<Disciplina> listaDisciplinas =  new ArrayList<>();
        listaDisciplinas.add(new Disciplina("Administração de Sistemas Proprietários", R.drawable.smile));
        listaDisciplinas.add(new Disciplina("Gerenciamento de Dados para Web", R.drawable.smile));
        listaDisciplinas.add(new Disciplina("Introdução à Conectividade", R.drawable.neutral));
        listaDisciplinas.add(new Disciplina("Programação para Banco de Dados", R.drawable.smile));
        listaDisciplinas.add(new Disciplina("Programação para Web Designers", R.drawable.sad));
        listaDisciplinas.add(new Disciplina("Programação para Web I", R.drawable.smile));
        return listaDisciplinas;
    }

    public List<Disciplina> obterDisciplinasPeriodo4(){
        List<Disciplina> listaDisciplinas =  new ArrayList<>();
        listaDisciplinas.add(new Disciplina("Gestão de Projetos", R.drawable.neutral));
        listaDisciplinas.add(new Disciplina("Programação para Web II", R.drawable.smile));
        listaDisciplinas.add(new Disciplina("Projeto de Interface Web", R.drawable.smile));
        listaDisciplinas.add(new Disciplina("Projeto de Sistemas", R.drawable.smile));
        listaDisciplinas.add(new Disciplina("Sistemas Distribuídos e SOA", R.drawable.smile));
        listaDisciplinas.add(new Disciplina("Testes de Software", R.drawable.sad));
        return listaDisciplinas;
    }

    public List<Disciplina> obterDisciplinasPeriodo5(){
        List<Disciplina> listaDisciplinas =  new ArrayList<>();
        listaDisciplinas.add(new Disciplina("Administração de Banco de Dados", R.drawable.smile));
        listaDisciplinas.add(new Disciplina("Infraestrutura para Sistemas Web", R.drawable.neutral));
        listaDisciplinas.add(new Disciplina("Metodologias de Desenvolvimento", R.drawable.sad));
        listaDisciplinas.add(new Disciplina("Novas Tecnologias em BD", R.drawable.smile));
        listaDisciplinas.add(new Disciplina("Programação para Dispositivos Móveis I", R.drawable.smile));
        listaDisciplinas.add(new Disciplina("Segurança de Sistemas", R.drawable.neutral));
        return listaDisciplinas;
    }

    public List<Disciplina> obterDisciplinasPeriodo6(){
        List<Disciplina> listaDisciplinas =  new ArrayList<>();
        listaDisciplinas.add(new Disciplina("Gerência de Configuração", R.drawable.smile));
        listaDisciplinas.add(new Disciplina("Novas Tecnologias em Desenvolvimento para Web", R.drawable.smile));
        listaDisciplinas.add(new Disciplina("Padrões de Projeto", R.drawable.smile));
        listaDisciplinas.add(new Disciplina("Programação para Dispositivos Móveis II", R.drawable.smile));
        return listaDisciplinas;
    }



}
