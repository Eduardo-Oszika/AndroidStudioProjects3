package com.oszika.planetalist.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.oszika.planetalist.R;
import com.oszika.planetalist.database.AppDAO;
import com.oszika.planetalist.database.AppDatabase;
import com.oszika.planetalist.database.InstanciaDB;
import com.oszika.planetalist.model.PlanetaModel;
import com.oszika.planetalist.model.UserPlanet;


public class PlanetDetalheFragment extends Fragment {

    private PlanetaModel planeta;
    private int progresso = 0;
    private AppDAO dao;


    public PlanetDetalheFragment(PlanetaModel planeta) {
        this.planeta = planeta;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppDatabase db = InstanciaDB.getInstance(getActivity().getApplicationContext());
        dao = db.appDAO();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_planet_detalhe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView = view.findViewById(R.id.img_planeta);
        imageView.setImageResource(planeta.getImagem());

        TextView textViewNome = view.findViewById(R.id.txt_nome_planeta);
        textViewNome.setText(planeta.getNome());

        TextView textGravidade = view.findViewById(R.id.txt_gravidade);
        textGravidade.setText("Gravidade: " + planeta.getGravidade());

        EditText editTextNome = view.findViewById(R.id.edt_seu_nome);
        EditText editTextDescricao = view.findViewById(R.id.edt_massa);
        ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        TextView textViewPesoResultado = view.findViewById(R.id.txt_peso_resultado);
        TextView textViewMassaResultado = view.findViewById(R.id.txt_massa_resultado);

        Button btnCalcular = view.findViewById(R.id.btn_calcular);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = editTextNome.getText().toString().trim();
                String massaTexto = editTextDescricao.getText().toString().trim();

                if (nome.isEmpty()) {
                    editTextNome.setError("Insira seu nome");
                    return;
                }

                if (massaTexto.isEmpty() || massaTexto.equals("0")) {
                    editTextDescricao.setError("Insira um valor válido");
                    return;
                }

                double massa = Double.parseDouble(editTextDescricao.getText().toString());


                executarProgressBar(progressBar, textViewPesoResultado,textViewMassaResultado, planeta.getStringMassaEmkg(massa), planeta.getStringPessoEmNewton(massa));

                Double pessoSuperfice = planeta.getPesoNoPlaneta();
                Double massaSuperfice = planeta.getMassaNoPlaneta();

                dao.inserirUserPlanet(new UserPlanet(nome,massaTexto, planeta.getNome(),pessoSuperfice, massaSuperfice));
            }
        });
    }

    private void executarProgressBar(ProgressBar progressBar, TextView textViewPesoResultado,TextView textViewMassaResultado, String massa, String peso) {
        progresso = 0;
        progressBar.setProgress(progresso);
        progressBar.setVisibility(View.VISIBLE);

        Handler handler = new Handler(Looper.getMainLooper());

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressBar.getProgress() < 100) {
                    progresso += 10;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progresso);
                            if (progresso == 100) {
                                progressBar.setVisibility(View.GONE);
                                textViewMassaResultado.setText(massa);
                                textViewPesoResultado.setText(peso);
                            }
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
