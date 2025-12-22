package com.oszika.planetalist.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oszika.planetalist.R;
import com.oszika.planetalist.adapter.MyAdapter;
import com.oszika.planetalist.model.PlanetaModel;

import java.util.ArrayList;

public class PlanetListFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_planet_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        String[] itens = getResources().getStringArray(R.array.itens);
        String[] itensGravidade = getResources().getStringArray(R.array.itens_gravidade);
        int[] imagems = getImagens();
        ArrayList<PlanetaModel> planetaList = getPlanetaList(itens, itensGravidade, imagems);
        MyAdapter adapter = new MyAdapter(getContext(), planetaList,new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = recyclerView.getChildAdapterPosition(view);
                PlanetaModel planeta = planetaList.get(i);
                PlanetDetalheFragment detalheFragment = new PlanetDetalheFragment(planeta);
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, detalheFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private int[] getImagens() {
        return new int[]{
                R.drawable.mercurio,
                R.drawable.venus,
                R.drawable.terra,
                R.drawable.marte,
                R.drawable.jupiter,
                R.drawable.saturno,
                R.drawable.urano,
                R.drawable.netuno
        };
    }


    private ArrayList<PlanetaModel> getPlanetaList(String[] itens, String[] itensGravidade, int[] imagems) {
        ArrayList<PlanetaModel> planetaList = new ArrayList<>();
        for (int i = 0; i < itens.length; i++) {
            PlanetaModel planeta = new PlanetaModel(itens[i], Double.valueOf(itensGravidade[i]), imagems[i]);
            planetaList.add(planeta);
        }
        return planetaList;
    }


}