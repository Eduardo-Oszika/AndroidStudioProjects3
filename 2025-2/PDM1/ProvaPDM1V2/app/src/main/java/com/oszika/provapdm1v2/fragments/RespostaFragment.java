package com.oszika.provapdm1v2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.oszika.provapdm1v2.activity.MainActivity;
import com.oszika.provapdm1v2.R;
import com.oszika.provapdm1v2.entity.Jogada;
import com.oszika.provapdm1v2.entity.Pergunta;
import com.oszika.provapdm1v2.entity.Usuario;
import com.oszika.provapdm1v2.service.ServiceJogadas;
import com.oszika.provapdm1v2.service.ServicePergunta;

import java.util.List;


public class RespostaFragment extends Fragment {

    private int posicaoAtual;
    private double pontuacao;
    private List<TextView> dicas;
    private EditText etResposta;
    private List<Pergunta> perguntas;
    private ServicePergunta servicePergunta;
    private int qdtDicasUsadas;
    private ProgressBar progressBar;
    private  Jogada jogada;
    private ServiceJogadas serviceJogada;

    public RespostaFragment() {
        posicaoAtual = 0;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        servicePergunta = new ServicePergunta(getActivity().getApplicationContext());
        serviceJogada = new ServiceJogadas(getActivity().getApplicationContext());
        jogada = new Jogada();
        MainActivity activity = (MainActivity) getActivity();
        jogada.idUsuario = activity.getUsuarioLogado().usuarioId;
        pontuacao = 0.0;
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
        etResposta = view.findViewById(R.id.et_resposta);
        TextView tvDica1 = view.findViewById(R.id.tv_dica1);
        TextView tvDica2 = view.findViewById(R.id.tv_dica2);
        TextView tvDica3 = view.findViewById(R.id.tv_dica3);
        progressBar = view.findViewById(R.id.progressBar);

        dicas = List.of(tvDica1, tvDica2, tvDica3);

        Button btnProximaPergunta = view.findViewById(R.id.Proximo);

        MainActivity activity = (MainActivity) getActivity();
        perguntas = servicePergunta.obterPerguntas();

        jogada.idPergunta1 = perguntas.get(0).perguntaId;
        jogada.idPergunta2 = perguntas.get(1).perguntaId;
        jogada.idPergunta3 = perguntas.get(2).perguntaId;
        jogada.idPergunta4 = perguntas.get(3).perguntaId;
        jogada.idPergunta5 = perguntas.get(4).perguntaId;
        preencerPergunta(tvEnunciado, tvDica1, tvDica2, tvDica3, perguntas, posicaoAtual);


        btnProximaPergunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posicaoAtual < perguntas.size()) {
                    if (verificaAcertou()) {
                        posicaoAtual++;
                        progressBar.setProgress(posicaoAtual);
                        if (posicaoAtual != perguntas.size())
                            preencerPergunta(tvEnunciado, tvDica1, tvDica2, tvDica3, perguntas, posicaoAtual);
                    }
                }
            }
        });


    }

    private boolean verificaAcertou() {
        String respostaUsuario = etResposta.getText().toString().toLowerCase().trim();
        String respostaCorreta = perguntas.get(posicaoAtual).Resposta.toLowerCase().trim();
        if (respostaUsuario.equals(respostaCorreta)) {
            double pontuacaoAtual = 2.0;
            for (int i = 0; i < dicas.size(); i++) {
                if (dicas.get(i).getVisibility() == View.VISIBLE)
                    pontuacaoAtual -= 0.5;
            }
            pontuacao += pontuacaoAtual;
            setPontuacaoPergunta(pontuacaoAtual);

            if (posicaoAtual == perguntas.size() -1) {
                jogada.pontuacao =pontuacao;
                serviceJogada.salvarJogada(jogada);
                Toast.makeText(getActivity(), "Salvou", Toast.LENGTH_SHORT).show();
            }
            return true;
        } else if (qdtDicasUsadas == 3) {
            setPontuacaoPergunta(0);
            return true;
        } else {
            qdtDicasUsadas++;
            dicas.get(qdtDicasUsadas - 1).setVisibility(View.VISIBLE);
        }


        return false;
    }

    private void setPontuacaoPergunta(double pontuacaoAtual) {
        if (posicaoAtual == 0)
            jogada.pontuacaoPergunta1 = pontuacaoAtual;
        else if (posicaoAtual == 1)
            jogada.pontuacaoPergunta2 = pontuacaoAtual;
        else if (posicaoAtual == 2)
            jogada.pontuacaoPergunta3 = pontuacaoAtual;
        else if (posicaoAtual == 3)
            jogada.pontuacaoPergunta4 = pontuacaoAtual;
        else if (posicaoAtual == 4)
            jogada.pontuacaoPergunta5 = pontuacaoAtual;
    }

    private void preencerPergunta(TextView tvEnunciado, TextView tvDica1, TextView tvDica2, TextView tvDica3, List<Pergunta> perguntas, int i) {
        Pergunta perguntaAtual = perguntas.get(i);
        tvEnunciado.setText(perguntaAtual.enunciado);
        for (int j = 0; j < dicas.size(); j++) {
            dicas.get(j).setVisibility(View.GONE);
            qdtDicasUsadas = 0;
        }
        tvDica1.setText(perguntaAtual.dica1);
        tvDica2.setText(perguntaAtual.dica2);
        tvDica3.setText(perguntaAtual.dica3);
        etResposta.setText("");
    }


}