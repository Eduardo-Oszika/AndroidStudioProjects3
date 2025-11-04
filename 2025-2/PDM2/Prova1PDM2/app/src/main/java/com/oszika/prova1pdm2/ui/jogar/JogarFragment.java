package com.oszika.prova1pdm2.ui.jogar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.oszika.prova1pdm2.databinding.FragmentJogarBinding;
import com.oszika.prova1pdm2.ui.SharedViewModel;

public class JogarFragment extends Fragment {

    private FragmentJogarBinding binding;
    JogarViewModel jogarViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        jogarViewModel =
                new ViewModelProvider(this).get(JogarViewModel.class);

        binding = FragmentJogarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedViewModel sharedViewModel =
                new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
//        final TextView textView = binding.textJogar;
//        jogarViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        jogarViewModel.setNumerosAtomicos(sharedViewModel.getElementos().getNumerosAtomicos());

        final TextView textView = binding.textNumeroParaSomar;
        jogarViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        final TextView textViewResposta = binding.tvResultado;
        jogarViewModel.getTextResposta().observe(getViewLifecycleOwner(), textViewResposta::setText);

        final LinearLayout llDigitarNumero = binding.llDigitarNumero;
        jogarViewModel.getLayout().observe(getViewLifecycleOwner(), llDigitarNumero::setVisibility);

        final LinearLayout llMostarNumero = binding.llMostrarNumero;
        jogarViewModel.getLayout2().observe(getViewLifecycleOwner(), llMostarNumero::setVisibility);

        final LinearLayout llResposta = binding.llResposta;
        jogarViewModel.getLayout3().observe(getViewLifecycleOwner(), llResposta::setVisibility);

        final LinearLayout llResultado = binding.llResultado;
        jogarViewModel.getLayout4().observe(getViewLifecycleOwner(), llResultado::setVisibility);


        Button buttonJogar = binding.btnJogar;
        Button buttonResponder = binding.btnResponder;

        buttonJogar.setOnClickListener(view -> startJogar());
        buttonResponder.setOnClickListener(view -> enviarResposta());



        return root;
    }

    private void enviarResposta() {
        int resposta = Integer.parseInt(binding.etNumeroRespondido.getText().toString());
        jogarViewModel.setLinearLayout3Visivel();
        jogarViewModel.verificarResposta(resposta);
    }


    private void startJogar() {
        int numero = Integer.parseInt(binding.etNumeroJogar.getText().toString());
        jogarViewModel.setLinearLayout2Visivel();
        jogarViewModel.incrementarNumero(numero);

    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}