package com.oszika.provapdm2v2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.oszika.provapdm2v2.databinding.FragmentHomeBinding;
import com.oszika.provapdm2v2.ui.entity.Personagem;
import com.oszika.provapdm2v2.util.adapter.MeuAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ArrayList<Personagem> lista;
    private MeuAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        lista = new ArrayList<>();
        adapter = new MeuAdapter(lista);
        binding.lvPersonagens.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.lvPersonagens.setAdapter(adapter);

        homeViewModel.getPersonagens().observe(getActivity(), new Observer<ArrayList<Personagem>>() {
            @Override
            public void onChanged(ArrayList<Personagem> personagems) {
                lista = personagems;
                adapter.setPersonagems(lista);
                homeViewModel.criarBanco(getActivity());
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