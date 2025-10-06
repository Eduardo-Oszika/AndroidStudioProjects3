package com.oszika.disciplinascomviewmodelenavigationdrawer.ui.primeiroPeriodo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.oszika.disciplinascomviewmodelenavigationdrawer.databinding.FragmentPrimeiroPeriodoBinding;
import com.oszika.disciplinascomviewmodelenavigationdrawer.ui.disciplina.Disciplina;
import com.oszika.disciplinascomviewmodelenavigationdrawer.ui.disciplina.DisciplinasAdapter;

import java.util.ArrayList;
import java.util.List;

public class PrimeiroPeriodoFragment extends Fragment {

    private DisciplinasAdapter adapter;
    private FragmentPrimeiroPeriodoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PrimeiroPeriodoViewModel primeiroPeriodoViewModel =
                new ViewModelProvider(this).get(PrimeiroPeriodoViewModel.class);

        binding = FragmentPrimeiroPeriodoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        adapter = new DisciplinasAdapter(getActivity(), new ArrayList<Disciplina>());
        binding.listViewPrimeiroPeriodo.setAdapter(adapter);

        primeiroPeriodoViewModel.getDisciplinas().observe(getActivity(), new Observer<List<Disciplina>>() {
            @Override
            public void onChanged(List<Disciplina> disciplinas) {

                adapter.clear();
                adapter.addAll(disciplinas);
                adapter.notifyDataSetChanged();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}