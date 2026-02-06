package com.oszika.provapdm1v2.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.oszika.provapdm1v2.activity.MainActivity;
import com.oszika.provapdm1v2.R;


public class SelecaoLoginFragment extends Fragment {


    public SelecaoLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_selecao_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        Button btnLoginUsuario = view.findViewById(R.id.btUsuarioLogin);
        Button btnLoginAdministrador = view.findViewById(R.id.btAdministadorLogin);

        MainActivity activity = (MainActivity) getActivity();

        btnLoginUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG","Clicou no botão de login do usuário");
                if (activity != null)
                    activity.mostrar(new LoginUsuarioFragment());
            }
        });
        btnLoginAdministrador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG","Clicou no botão de login do administrador");
                if (activity != null)
                    activity.mostrar(new LoginAdministradorFragment());

            }
        });

    }


}