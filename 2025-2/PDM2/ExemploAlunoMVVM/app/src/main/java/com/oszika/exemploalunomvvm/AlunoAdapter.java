package com.oszika.exemploalunomvvm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AlunoAdapter extends ArrayAdapter<Aluno> {
    private Context context;
    private List<Aluno> alunos;


    public AlunoAdapter(@NonNull Context context, List<Aluno> alunos) {
        super(context,0);
        this.context = context;
        this.alunos = alunos;
    }//


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Aluno aluno = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_aluno,parent,false);
        }//if
        TextView textViewNome = convertView.findViewById(R.id.textViewNome);
        TextView textViewNota = convertView.findViewById(R.id.textViewNota);
        if(aluno!=null){
            textViewNome.setText(aluno.getNome());
            textViewNota.setText("Nota: "+aluno.getNota());
        }//if
        return convertView;
    }
}//class