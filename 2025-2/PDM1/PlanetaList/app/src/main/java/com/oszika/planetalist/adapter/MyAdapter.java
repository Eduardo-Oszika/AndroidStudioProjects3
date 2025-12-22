package com.oszika.planetalist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oszika.planetalist.R;
import com.oszika.planetalist.fragment.PlanetDetalheFragment;
import com.oszika.planetalist.model.PlanetaModel;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private ArrayList<PlanetaModel> items;
    private View.OnClickListener listener;

    public MyAdapter(Context context, ArrayList<PlanetaModel> items, View.OnClickListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlanetaModel itemModel = items.get(position);
        holder.img.setImageResource(itemModel.getImagem());
        holder.txt.setText(itemModel.getNome());

        holder.itemView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView txt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img = itemView.findViewById(R.id.imageView);
            this.txt = itemView.findViewById(R.id.textView);
        }
    }
}