package com.oszika.provapdm1v2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.oszika.provapdm1v2.MainActivity;
import com.oszika.provapdm1v2.R;
import com.oszika.provapdm1v2.dao.AppDao;
import com.oszika.provapdm1v2.dialog.AlertDialogLogin;
import com.oszika.provapdm1v2.entity.Usuario;
import com.oszika.provapdm1v2.service.ServiceUsuario;


public class LoginUsuarioFragment extends Fragment {
    ServiceUsuario serviceUsuario;

    public LoginUsuarioFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serviceUsuario = new ServiceUsuario(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_usuario, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText username, senha;
        Button btnLoginUser, btnCadastrarUser;
        MainActivity activity = (MainActivity) getActivity();
        username = view.findViewById(R.id.et_user_usuario);
        senha = view.findViewById(R.id.et_user_senha);
        btnLoginUser = view.findViewById(R.id.btnUserLogin);
        btnCadastrarUser = view.findViewById(R.id.btnCadastroUsuario);

        btnLoginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = senha.getText().toString();

                if (activity != null) {
                    if (verificaCampos(user, pass, username, senha)) {
                        Usuario usuario = serviceUsuario.autenticarUsuario(user, pass);
                        if (usuario != null && !usuario.isAdministrador()) {
                            Toast.makeText(activity, "Login de usuario bem-sucedido!", Toast.LENGTH_SHORT).show();
                            activity.mostrar(new RespostaFragment());
                        } else {
                            openAlertDialog();
                        }
                    }
                }
            }
        });

        btnCadastrarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = senha.getText().toString();

                if (verificaCampos(user, pass, username, senha)) {
                    if (activity != null) {
                        serviceUsuario.inserirUsuario(new Usuario(user, pass, "Usuario"));
                        limparCampos(username, senha);
                    }
                }
            }
        });
    }

    private void limparCampos(EditText username, EditText senha) {
        username.setText("");
        senha.setText("");
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


