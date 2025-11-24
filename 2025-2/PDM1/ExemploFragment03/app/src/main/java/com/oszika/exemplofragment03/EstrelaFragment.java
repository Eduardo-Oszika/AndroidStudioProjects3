package com.oszika.exemplofragment03;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EstrelaFragment extends Fragment {
    private static final String ARG_NOME = "nome";
    private static final String ARG_DESCRICAO = "descricao";
    private String estrelaNome, estrelaDescricao;


    public static EstrelaFragment newInstance(String nome, String descricao) {
        EstrelaFragment fragment = new EstrelaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NOME, nome);
        args.putString(ARG_DESCRICAO, descricao);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            estrelaNome = getArguments().getString(ARG_NOME);
            estrelaDescricao = getArguments().getString(ARG_DESCRICAO);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_estrela, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textViewNome = view.findViewById(R.id.textviewNomeEstrela);
        TextView textViewDescricao = view.findViewById(R.id.textViewDescricao);
        textViewNome.setText(estrelaNome);
        textViewDescricao.setText(estrelaDescricao);
    }
}
