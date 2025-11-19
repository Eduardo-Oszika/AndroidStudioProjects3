package com.oszika.exemplofragment03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MeuAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Estrela> itens;

    public MeuAdapter(Context context, List<Estrela> itens) {
        this.inflater = LayoutInflater.from(context);
        this.itens = itens;
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int i) {
        return itens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
            TextView textView = view.findViewById(android.R.id.text1);
            textView.setText(itens.get(i).getNome());
        }
        return view;
    }
}
