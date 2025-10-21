package com.oszika.prova1pdm2.ui.embaralhar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.oszika.prova1pdm2.databinding.FragmentEmbaralharBinding;
import com.oszika.prova1pdm2.ui.elemento.Elementos;
import com.oszika.prova1pdm2.ui.home.HomeViewModel;

import java.util.ArrayList;

public class EmbaralharFragment extends Fragment {

    private FragmentEmbaralharBinding binding;
    private ArrayList<String> lista;
    private ArrayAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EmbaralharViewModel embaralharViewModel =
                new ViewModelProvider(requireActivity()).get(EmbaralharViewModel.class);

        binding = FragmentEmbaralharBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        lista = new ArrayList<>();

        HomeViewModel homeViewModel =
                new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        Elementos elementos = homeViewModel.getElementos();
        embaralharViewModel.setElementos(elementos);
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, lista);

        binding.lvEmbaralhado.setAdapter(adapter);



        embaralharViewModel.getElementosEmbaralhados().observe(getActivity(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                lista = strings;
                adapter.clear();
                adapter.addAll(lista);
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