package com.oszika.prova1pdm2.ui.embaralhar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.oszika.prova1pdm2.databinding.FragmentEmbaralharBinding;
import com.oszika.prova1pdm2.ui.SharedViewModel;
import com.oszika.prova1pdm2.ui.elemento.Elementos;

import java.util.ArrayList;

public class EmbaralharFragment extends Fragment {

    private FragmentEmbaralharBinding binding;
    private ArrayList<String> lista;
    private ArrayAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EmbaralharViewModel embaralharViewModel =
                new ViewModelProvider(this).get(EmbaralharViewModel.class);

        binding = FragmentEmbaralharBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        lista = new ArrayList<>();

        SharedViewModel sharedViewModel =
                new ViewModelProvider(requireActivity()).get(SharedViewModel.class);


        Elementos elementos = sharedViewModel.getElementos();
        embaralharViewModel.setElementos(elementos);
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, lista);

        binding.lvEmbaralhado.setAdapter(adapter);



        embaralharViewModel.getStringElementosEmbaralhados().observe(getActivity(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                lista = strings;
                adapter.clear();
                adapter.addAll(lista);
                adapter.notifyDataSetChanged();
                sharedViewModel.setElementosEmbaralhados(embaralharViewModel.getElementosEmbaralhados());
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