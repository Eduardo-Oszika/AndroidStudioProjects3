package com.oszika.exemplolistapersonalizada;

import java.util.ArrayList;
import java.util.List;

public class RepositorioItem {
    public static List<ItemLista> getItens() {
         List<ItemLista> itens = new ArrayList<>();
        itens.add(new ItemLista("cafe", R.drawable.cafe));
        itens.add(new ItemLista("bolo", R.drawable.bolo));
        itens.add(new ItemLista("cafe", R.drawable.cafe));
        itens.add(new ItemLista("bolo", R.drawable.bolo));

        return itens;
    }
}
