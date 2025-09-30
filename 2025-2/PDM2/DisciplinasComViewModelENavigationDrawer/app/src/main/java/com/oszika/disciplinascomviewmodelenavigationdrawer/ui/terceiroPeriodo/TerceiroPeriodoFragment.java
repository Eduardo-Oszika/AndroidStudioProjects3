package com.oszika.disciplinascomviewmodelenavigationdrawer.ui.terceiroPeriodo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.oszika.disciplinascomviewmodelenavigationdrawer.databinding.FragmentTerceiroPeriodoBinding;

public class TerceiroPeriodoFragment extends Fragment {

    private FragmentTerceiroPeriodoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TerceiroPeriodoViewModel slideshowViewModel =
                new ViewModelProvider(this).get(TerceiroPeriodoViewModel.class);

        binding = FragmentTerceiroPeriodoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTerceiroPeriodo;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}