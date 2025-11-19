package com.oszika.exemplofragment03;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class EstrelaRepositorio {
    private Context context;

    public EstrelaRepositorio(Context context) {
        this.context = context;
    }

    public List<Estrela> obterEstrelas(){
        List<Estrela> estrelas = new ArrayList<>();
        Resources res = context.getResources();
        estrelas.add(new Estrela(res.getString(R.string.estrela_1_nome), res.getString(R.string.estrela_1_descricao)));
        estrelas.add(new Estrela(res.getString(R.string.estrela_2_nome), res.getString(R.string.estrela_2_descricao)));
        estrelas.add(new Estrela(res.getString(R.string.estrela_3_nome), res.getString(R.string.estrela_3_descricao)));
        return estrelas;
    }
}//class