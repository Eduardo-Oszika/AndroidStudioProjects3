package com.oszika.revisaoprovapdm2.util.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.oszika.revisaoprovapdm2.R;
import com.oszika.revisaoprovapdm2.entity.Resultado;

import java.util.ArrayList;
import java.util.List;

public class MeuAdapter extends RecyclerView.Adapter<MeuAdapter.ViewHolder> {
    private List<Resultado> itens;

    public MeuAdapter(List<Resultado> itens) {
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

        Resultado item = itens.get(position);
        holder.getTextName().setText(item.nomeAluno);
        holder.getTextNotaFinal().setText(item.getNotaFinalFormatada());
        holder.getTextStatus().setText(item.status);

    }

    @Override
    public long getItemId(int i) {
        return itens.get(i).id;
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    public void setResultado(ArrayList<Resultado> lista) {
        this.itens = lista;
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textName, textNotaFinal, textStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.tvNome);
            textNotaFinal = itemView.findViewById(R.id.tvNotaFinal);
            textStatus = itemView.findViewById(R.id.tvStatus);
        }

        public TextView getTextName() {
            return textName;
        }

        public void setTextName(TextView textName) {
            this.textName = textName;
        }

        public TextView getTextNotaFinal() {
            return textNotaFinal;
        }

        public void setTextNotaFinal(TextView textNotaFinal) {
            this.textNotaFinal = textNotaFinal;
        }

        public TextView getTextStatus() {
            return textStatus;
        }

        public void setTextStatus(TextView textStatus) {
            this.textStatus = textStatus;
        }
    }
}
