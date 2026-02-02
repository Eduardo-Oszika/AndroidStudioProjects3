package com.oszika.provapdm1v2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.oszika.provapdm1v2.MainActivity;
import com.oszika.provapdm1v2.R;
import com.oszika.provapdm1v2.dao.AppDao;
import com.oszika.provapdm1v2.entity.Pergunta;
import com.oszika.provapdm1v2.service.ServicePergunta;

import java.util.List;


public class RespostaFragment extends Fragment {

    int posicaoAtual;
    ServicePergunta servicePergunta;
    public RespostaFragment() {
        posicaoAtual = 0;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        servicePergunta = new ServicePergunta(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_resposta, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvEnunciado = view.findViewById(R.id.tv_enunciado);
        EditText etResposta = view.findViewById(R.id.et_resposta);
        TextView tvDica1 = view.findViewById(R.id.tv_dica1);
        TextView tvDica2 = view.findViewById(R.id.tv_dica2);
        TextView tvDica3 = view.findViewById(R.id.tv_dica3);

        Button btnEnviarResposta = view.findViewById(R.id.btn_enviar_resposta);

        Button btnProximaPergunta = view.findViewById(R.id.Proximo);
        Button btnAnteriorPergunta = view.findViewById(R.id.Anterior);

        MainActivity activity = (MainActivity) getActivity();
        List<Pergunta> perguntas = servicePergunta.obterPerguntas();

        preencerPergunta(tvEnunciado, tvDica1, tvDica2, tvDica3, perguntas, posicaoAtual);

        btnProximaPergunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posicaoAtual < perguntas.size() - 1) {
                    posicaoAtual++;
                    preencerPergunta(tvEnunciado, tvDica1, tvDica2, tvDica3, perguntas, posicaoAtual);
                }
            }
        });
        btnAnteriorPergunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posicaoAtual > 0) {
                    posicaoAtual--;
                    preencerPergunta(tvEnunciado, tvDica1, tvDica2, tvDica3, perguntas, posicaoAtual);
                }
            }
        });


    }

    private void preencerPergunta(TextView tvEnunciado, TextView tvDica1, TextView tvDica2, TextView tvDica3, List<Pergunta> perguntas, int i) {
        Pergunta perguntaAtual = perguntas.get(i);
        tvEnunciado.setText(perguntaAtual.enunciado);
        tvDica1.setText(perguntaAtual.dica1);
        tvDica2.setText(perguntaAtual.dica2);
        tvDica3.setText(perguntaAtual.dica3);
    }


}