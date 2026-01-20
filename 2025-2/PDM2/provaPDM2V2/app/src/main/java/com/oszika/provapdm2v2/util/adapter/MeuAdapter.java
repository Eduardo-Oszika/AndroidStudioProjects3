package com.oszika.provapdm2v2.util.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.oszika.provapdm2v2.R;
import com.oszika.provapdm2v2.ui.entity.Personagem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MeuAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Personagem> itens;

    public MeuAdapter(Context context, List<Personagem> itens) {
        this.inflater = LayoutInflater.from(context);
        this.itens = itens;
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Personagem getItem(int i) {
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

        Personagem item = itens.get(i);

        TextView textId = view.findViewById(R.id.tvId);
        if (item.getId() != null){
            textId.setText(String.valueOf(item.getId()));
        }

        TextView texto = view.findViewById(R.id.tvItem);
        texto.setText(item.getName());

        ImageView img = view.findViewById(R.id.imgItem);
        Picasso.get()
                .load(item.getImageUrl())
                .resize(100, 100)
                .centerCrop()
                .into(img);

        return view;
    }
}
