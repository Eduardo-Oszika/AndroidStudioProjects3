package com.oszika.disciplinascomviewmodelenavigationdrawer.ui.disciplina;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.oszika.disciplinascomviewmodelenavigationdrawer.R;

import java.util.List;

public class DisciplinasAdapter extends ArrayAdapter<Disciplina> {
    private Context context;
    private List<Disciplina> disciplinas;

    public DisciplinasAdapter(@NonNull Context context, List<Disciplina> disciplinas) {
        super(context,0);
        this.context = context;
        this.disciplinas = disciplinas;
    }//

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Disciplina disciplina = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_disciplina,parent,false);
        }//if
        TextView textDisciplina = convertView.findViewById(R.id.textDisciplina);
        ImageView imgEmoji = convertView.findViewById(R.id.imgDisciplina);
        if(disciplina!=null){
            textDisciplina.setText(disciplina.getNome());
            imgEmoji.setImageResource(disciplina.getImg());
        }//if
        return convertView;
    }
}
