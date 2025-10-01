package com.oszika.exemplolistapersonalizada;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MeuAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<ItemLista> itens;

    public MeuAdapter(Context context, List<ItemLista> itens) {
        this.inflater = LayoutInflater.from(context);
        this.itens = itens;
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public ItemLista getItem(int i) {
        return itens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.item_lista, viewGroup, false);
        }

        ItemLista item = itens.get(i);
        TextView texto = view.findViewById(R.id.tvItem);
        texto.setText(item.getNome());

        ImageView img = view.findViewById(R.id.imgItem);
        img.setImageResource(item.getImagem());
        return view;
    }
}
