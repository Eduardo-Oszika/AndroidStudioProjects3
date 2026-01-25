package com.oszika.provapdm2v2.util.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oszika.provapdm2v2.R;
import com.oszika.provapdm2v2.ui.entity.Personagem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MeuAdapter extends RecyclerView.Adapter<MeuAdapter.ViewHolder> {
    private List<Personagem> itens;

    public MeuAdapter(List<Personagem> itens) {
        this.itens = itens;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView textId = holder.getTextId();
        Personagem item = itens.get(position);
        if (item.getId() != null) {
            textId.setText(String.valueOf(item.getId()).trim());
        }
        TextView texto = holder.getTextName();
        texto.setText(item.getName().trim());

        ImageView img = holder.getImageView();
        Picasso.get()
                .load(item.getImageUrl())
                .resize(200, 200)
                .centerCrop()
                .into(img);

    }

    @Override
    public long getItemId(int i) {
        return itens.get(i).getId();
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    public void setPersonagems(ArrayList<Personagem> lista) {
        this.itens = lista;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textName, textId;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.tvItem);
            textId = itemView.findViewById(R.id.tvId);
            imageView = itemView.findViewById(R.id.imgItem);
        }

        public TextView getTextName() {
            return textName;
        }

        public void setTextName(TextView textName) {
            this.textName = textName;
        }

        public TextView getTextId() {
            return textId;
        }

        public void setTextId(TextView textId) {
            this.textId = textId;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }
    }
}
