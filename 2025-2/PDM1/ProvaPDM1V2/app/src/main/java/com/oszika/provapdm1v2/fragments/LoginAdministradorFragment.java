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

import com.oszika.provapdm1v2.activity.MainActivity;
import com.oszika.provapdm1v2.R;
import com.oszika.provapdm1v2.dialog.AlertDialogLogin;
import com.oszika.provapdm1v2.entity.Usuario;
import com.oszika.provapdm1v2.service.ServiceUsuario;


public class LoginAdministradorFragment extends Fragment {

    public LoginAdministradorFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_administrador, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText username, senha;
        Button btnLoginAdmin;
        MainActivity activity = (MainActivity) getActivity();
        username = view.findViewById(R.id.et_adm_usuario);
        senha = view.findViewById(R.id.et_adm_senha);
        btnLoginAdmin = view.findViewById(R.id.btnAdmLogin);

        btnLoginAdmin.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = senha.getText().toString();


                if(activity != null) {
                    if (verificaCampos(user, pass, username, senha)) {
                        ServiceUsuario serviceUsuario = new ServiceUsuario(activity.getApplicationContext());

                        Usuario usuario = serviceUsuario.autenticarUsuario(user, pass);
                        if (usuario!= null && usuario.isAdministrador()) {
                            Toast.makeText(activity, "Login de administrador bem-sucedido!", Toast.LENGTH_SHORT).show();
                            activity.mostrar(new CPerguntaFragment());
                        } else {
                            openAlertDialog();
                        }
                    }
                }
            }
        });
    }

    private void openAlertDialog() {
        AlertDialogLogin mdf = new AlertDialogLogin();
        mdf.show(getActivity().getSupportFragmentManager(), "personalizado");
    }

    private Boolean verificaCampos(String user, String pass, EditText username, EditText senha) {
        if (user.isEmpty()) {
            username.setError("O campo usuário é obrigatório");
        }
        if (pass.isEmpty()) {
            senha.setError("O campo senha é obrigatório");
        }
        return !user.isEmpty() && !pass.isEmpty();

    }


}