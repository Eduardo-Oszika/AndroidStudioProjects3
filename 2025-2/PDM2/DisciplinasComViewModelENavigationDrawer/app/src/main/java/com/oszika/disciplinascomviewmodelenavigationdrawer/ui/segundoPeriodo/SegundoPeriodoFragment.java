package com.oszika.disciplinascomviewmodelenavigationdrawer.ui.segundoPeriodo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.oszika.disciplinascomviewmodelenavigationdrawer.databinding.FragmentSegundoPeriodoBinding;

public class SegundoPeriodoFragment extends Fragment {

    private FragmentSegundoPeriodoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SegundoPeriodoViewModel galleryViewModel =
                new ViewModelProvider(this).get(SegundoPeriodoViewModel.class);

        binding = FragmentSegundoPeriodoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSegundoPeriodo;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}