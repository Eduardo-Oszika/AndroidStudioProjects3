package com.oszika.provapdm2v2.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.oszika.provapdm2v2.MainActivity;
import com.oszika.provapdm2v2.databinding.FragmentGalleryBinding;
import com.oszika.provapdm2v2.ui.entity.Personagem;
import com.oszika.provapdm2v2.util.adapter.MeuAdapterPersonagem;
import com.oszika.provapdm2v2.util.notification.NotificationHelper;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private ArrayList<Personagem> lista;
    private MeuAdapterPersonagem adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        NotificationHelper.criarCanal(getActivity());

        lista = new ArrayList<>();
        adapter = new MeuAdapterPersonagem(lista);
        binding.lvPersonagens.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.lvPersonagens.setAdapter(adapter);

        binding.btnGerarJogada.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryViewModel.obterBanco(getContext().getApplicationContext());
                if (verificarCampos()) {
                    String s = galleryViewModel.gerarJogada(Integer.parseInt(binding.numeroJogador.getText().toString()), Integer.parseInt(binding.numeroAndroid.getText().toString()));
                    if (!s.isEmpty()) {
                        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                    }
                    else
                        binding.jogar.setVisibility(View.VISIBLE);
                }
            }
        });



        galleryViewModel.getPersonagens().observe(requireActivity(), new Observer<ArrayList<Personagem>>() {
            @Override
            public void onChanged(ArrayList<Personagem> personagems) {
                lista = personagems;
                adapter.setPersonagems(lista);
            }
        });

        galleryViewModel.getJogadaLive().observe(requireActivity(), new Observer<com.oszika.provapdm2v2.ui.entity.Jogada>() {
            @Override
            public void onChanged(com.oszika.provapdm2v2.ui.entity.Jogada jogada) {
                String mensagem = jogada.resultado;
                gerar(getView(), mensagem);
            }
        });
        binding.jogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryViewModel.jogar(binding.nomeJogada.getText().toString());
            }
        });




        return root;
    }

    private boolean verificarCampos() {
        TextView nomeJogador = binding.nomeJogada;
        TextView numeroJogador = binding.numeroJogador;
        TextView numeroAndroid = binding.numeroAndroid;
        boolean ok = true;
        if (numeroJogador.getText().toString().isEmpty()) {
            numeroJogador.setError("Campo obrigatório");
            ok = false;
        }
        if (numeroAndroid.getText().toString().isEmpty()) {
            numeroAndroid.setError("Campo obrigatório");
            ok = false;
        }
        if (nomeJogador.getText().toString().isEmpty()) {
            nomeJogador.setError("Campo obrigatório");
            ok = false;
        }
        return ok;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void gerar(View view, String mensagem) {
        MainActivity activity = (MainActivity) getActivity();
        if (activity!= null) {
            if (activity.verificarPermissaoNotificacao()) {
                NotificationHelper.gerarNotificacao(getActivity(),
                        "Quem ganhou?", mensagem
                );
            } else {
                Toast.makeText(getActivity(),
                        "Ative a permissão!!!",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

}