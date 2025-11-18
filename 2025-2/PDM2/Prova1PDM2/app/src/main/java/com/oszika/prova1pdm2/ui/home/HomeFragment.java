package com.oszika.prova1pdm2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.oszika.prova1pdm2.databinding.FragmentHomeBinding;
import com.oszika.prova1pdm2.ui.SharedViewModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {

    private FragmentHomeBinding binding;
    private ArrayList<String> lista;
    private ArrayAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        lista = new ArrayList<>();

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, lista);

        binding.lvElementos.setAdapter(adapter);

        SharedViewModel sharedViewModel =
                new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        homeViewModel.getElementosText().observe(getActivity(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                lista = strings;
                adapter.clear();
                adapter.addAll(lista);
                adapter.notifyDataSetChanged();
                sharedViewModel.setElementos(homeViewModel.getElementos());
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}