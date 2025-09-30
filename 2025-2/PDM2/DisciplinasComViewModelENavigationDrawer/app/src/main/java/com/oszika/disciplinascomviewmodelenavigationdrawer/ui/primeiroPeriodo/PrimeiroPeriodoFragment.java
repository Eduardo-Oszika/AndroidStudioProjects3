package com.oszika.disciplinascomviewmodelenavigationdrawer.ui.primeiroPeriodo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.oszika.disciplinascomviewmodelenavigationdrawer.databinding.FragmentPrimeiroPeriodoBinding;

public class PrimeiroPeriodoFragment extends Fragment {

    private FragmentPrimeiroPeriodoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PrimeiroPeriodoViewModel homeViewModel =
                new ViewModelProvider(this).get(PrimeiroPeriodoViewModel.class);

        binding = FragmentPrimeiroPeriodoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textPrimeiroPeriodo;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}