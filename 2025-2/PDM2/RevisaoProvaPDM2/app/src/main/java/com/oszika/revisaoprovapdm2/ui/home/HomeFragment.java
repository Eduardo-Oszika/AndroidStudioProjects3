package com.oszika.revisaoprovapdm2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.oszika.revisaoprovapdm2.R;
import com.oszika.revisaoprovapdm2.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnUserLogin.setOnClickListener(v -> {

            String user = binding.etUserUsuario.getText().toString();
            String senha = binding.etUserSenha.getText().toString();

            homeViewModel.login(requireContext(), user, senha);
        });

        binding.btnCadastroUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = binding.etUserUsuario.getText().toString();
                String senha = binding.etUserSenha.getText().toString();

                homeViewModel.cadastro(requireContext(), user, senha);

            }
        });

        homeViewModel.getCadastroSucesso().observe(getViewLifecycleOwner(), sucesso -> {
            if (sucesso != null && sucesso) {
                Toast.makeText(getContext(), "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });

        homeViewModel.getErro().observe(getViewLifecycleOwner(), mensagem -> {
            if (mensagem != null) {
                Toast.makeText(getContext(), mensagem, Toast.LENGTH_SHORT).show();
            }
        });

        homeViewModel.getLoginSucesso().observe(getViewLifecycleOwner(), sucesso -> {
            if (sucesso != null && sucesso) {
                NavHostFragment.findNavController(this)
                        .navigate(R.id.nav_slideshow);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}