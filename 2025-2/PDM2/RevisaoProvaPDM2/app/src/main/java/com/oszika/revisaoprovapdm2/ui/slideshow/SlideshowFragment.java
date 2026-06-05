package com.oszika.revisaoprovapdm2.ui.slideshow;

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

import com.oszika.revisaoprovapdm2.MainActivity;
import com.oszika.revisaoprovapdm2.databinding.FragmentSlideshowBinding;
import com.oszika.revisaoprovapdm2.entity.Resultado;
import com.oszika.revisaoprovapdm2.pojo.AlunoPojo;
import com.oszika.revisaoprovapdm2.util.adapter.MeuAdapter;
import com.oszika.revisaoprovapdm2.util.notification.NotificationHelper;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private ArrayList<AlunoPojo> alunos;
    private ArrayList<Resultado> lista;
    private MeuAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        lista = new ArrayList<>();
        adapter = new MeuAdapter(lista);
        binding.lvResultado.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.lvResultado.setAdapter(adapter);

        binding.btnResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideshowViewModel.converterResultados();
            }
        });

        slideshowViewModel.getResultado().observe(requireActivity(), new Observer<ArrayList<Resultado>>() {
            @Override
            public void onChanged(ArrayList<Resultado> resultados) {
                lista = resultados;
                adapter.setResultado(lista);
                slideshowViewModel.salvarResultados(getContext(), resultados);
            }
        });

        slideshowViewModel.getCadastroSucesso().observe(requireActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean sucesso) {
                if (sucesso) {
                    gerar(getView(), sucesso);
                } else {
                    Toast.makeText(getActivity(), "erro ao salvar os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return root;
    }

    private void gerar(View view, Boolean sucesso) {
        MainActivity activity = (MainActivity) getActivity();
        if (activity!= null) {
            if (activity.verificarPermissaoNotificacao()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Alunos aprovados:\n");
                for (Resultado r : lista) {
                    if (r.status.equals("Aprovado")) {
                        sb.append(r.nomeAluno).append("\n");
                    }
                }
                NotificationHelper.gerarNotificacao(getActivity(),
                        "Alunos Aprovados", sb.toString()
                );
            } else {
                Toast.makeText(getActivity(),
                        "Ative a permissão!!!",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}