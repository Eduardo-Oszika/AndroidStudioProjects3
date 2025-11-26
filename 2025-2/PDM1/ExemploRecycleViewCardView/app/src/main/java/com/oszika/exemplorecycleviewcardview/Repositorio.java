package com.oszika.exemplorecycleviewcardview;

import java.util.ArrayList;

public class Repositorio {
    public static ArrayList<ItemModel> obterItens() {
        ArrayList<ItemModel> listaItens = new ArrayList<>();
        listaItens.add(new ItemModel("Item 1", R.mipmap.ic_launcher));
        listaItens.add(new ItemModel("Item 2", R.mipmap.ic_launcher));
        listaItens.add(new ItemModel("Item 3", R.mipmap.ic_launcher));
        return listaItens;
    }
}
