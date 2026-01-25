package com.oszika.provapdm1v2.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oszika.provapdm1v2.MainActivity;
import com.oszika.provapdm1v2.R;
import com.oszika.provapdm1v2.dao.AppDao;
import com.oszika.provapdm1v2.entity.Pergunta;

public class CPerguntaFragment extends Fragment {


    public CPerguntaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_c_pergunta, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText enunciado,resposta,dica1,dica2,dica3;
        enunciado = view.findViewById(R.id.et_cpergunta_enunciado);
        resposta = view.findViewById(R.id.et_cpergunta_resposta);
        dica1 = view.findViewById(R.id.et_cpergunta_dica1);
        dica2 = view.findViewById(R.id.et_cpergunta_dica2);
        dica3 = view.findViewById(R.id.et_cpergunta_dica3);

        Button btnSalvarPergunta = view.findViewById(R.id.btn_cpergunta_cadastrar);
        btnSalvarPergunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String enun = enunciado.getText().toString();
                String resp = resposta.getText().toString();
                String d1 = dica1.getText().toString();
                String d2 = dica2.getText().toString();
                String d3 = dica3.getText().toString();

                if (verificarCampos(enun, resp, d1, d2, d3, enunciado, resposta, dica1, dica2, dica3)){
                    MainActivity activity = (MainActivity) getActivity();
                    if (activity != null) {
                        AppDao dao = activity.getDao();
                        Long l = dao.inserirPergunta(new Pergunta(enun, resp, d1, d2, d3));
                        if (l > 0)
                            Toast.makeText(activity, "Pergunta cadastrada com sucesso!", Toast.LENGTH_SHORT).show();

                        limparCampos(enunciado, resposta, dica1, dica2, dica3);
                    }
                }
            }
        });

    }

    private void limparCampos(EditText enunciado, EditText resposta, EditText dica1, EditText dica2, EditText dica3) {
        enunciado.setText("");
        resposta.setText("");
        dica1.setText("");
        dica2.setText("");
        dica3.setText("");
    }

    private Boolean verificarCampos(String enun, String resp, String d1, String d2, String d3, EditText enunciado, EditText resposta, EditText dica1, EditText dica2, EditText dica3) {
        if (enun.isEmpty()) {
            enunciado.setError("O campo usuário é obrigatório");
        }
        if (resp.isEmpty()) {
            resposta.setError("O campo senha é obrigatório");
        }
        if (d1.isEmpty()) {
            dica1.setError("O campo dica 1 é obrigatório");
        }
        if (d2.isEmpty()) {
            dica2.setError("O campo dica 2 é obrigatório");
        }
        if (d3.isEmpty()) {
            dica3.setError("O campo dica 3 é obrigatório");
        }
        return !enun.isEmpty() && !resp.isEmpty() && !d1.isEmpty() && !d2.isEmpty() && !d3.isEmpty();
    }


}