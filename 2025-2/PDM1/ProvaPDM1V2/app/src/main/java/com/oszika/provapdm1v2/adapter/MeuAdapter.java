package com.oszika.provapdm1v2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oszika.provapdm1v2.R;
import com.oszika.provapdm1v2.entity.Jogada;
import com.oszika.provapdm1v2.entity.Pergunta;

import java.util.List;

public class MeuAdapter extends RecyclerView.Adapter<MeuAdapter.ViewHolder> {
    private List<Pergunta> itens;
    private Jogada jogada;

    public MeuAdapter(List<Pergunta> itens, Jogada jogada) {
        this.itens = itens;
        this.jogada = jogada;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView textNumeroQuestao, textResultado, textPontuacao;
        textNumeroQuestao = holder.getTextNumeroQuestao();
        textResultado = holder.getTextResultado();
        textPontuacao = holder.getTextPontuacao();

        Pergunta item = itens.get(position);

        if (item.perguntaId != 0) {
            int posicaoceta = position + 1;
            textNumeroQuestao.setText("Numero " + posicaoceta);
        }
        double pontuacaoPorQuestao = obterPontuacaoPorQuestao(item);
        if (pontuacaoPorQuestao > 0)
            textResultado.setText("Acertou");
        else
            textResultado.setText("Errou");
        String pontuacaoStr = String.format("%.2f", pontuacaoPorQuestao);
        textPontuacao.setText("Pontuação: " + pontuacaoStr);


    }

    private double obterPontuacaoPorQuestao(Pergunta item) {
        if (jogada.idPergunta1 == item.perguntaId) {
            return jogada.pontuacaoPergunta1;
        } else if (jogada.idPergunta2 == item.perguntaId) {
            return jogada.pontuacaoPergunta2;
        } else if (jogada.idPergunta3 == item.perguntaId) {
            return jogada.pontuacaoPergunta3;
        } else if (jogada.idPergunta4 == item.perguntaId) {
            return jogada.pontuacaoPergunta4;
        } else if (jogada.idPergunta5 == item.perguntaId) {
            return jogada.pontuacaoPergunta5;
        }
        return 0;
    }

    @Override
    public long getItemId(int i) {
        return itens.get(i).perguntaId;
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textNumeroQuestao, textResultado, textPontuacao;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textNumeroQuestao = itemView.findViewById(R.id.tv_numero_questao);
            textResultado = itemView.findViewById(R.id.tv_resultado_questao);
            textPontuacao = itemView.findViewById(R.id.tv_pontuacao_questao);
        }

        public TextView getTextNumeroQuestao() {
            return textNumeroQuestao;
        }

        public void setTextNumeroQuestao(TextView textNumeroQuestao) {
            this.textNumeroQuestao = textNumeroQuestao;
        }

        public TextView getTextResultado() {
            return textResultado;
        }

        public void setTextResultado(TextView textResultado) {
            this.textResultado = textResultado;
        }

        public TextView getTextPontuacao() {
            return textPontuacao;
        }

        public void setTextPontuacao(TextView textPontuacao) {
            this.textPontuacao = textPontuacao;
        }
    }
}
