package com.oszika.revisaoprovapdm2.ui.gallery;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.oszika.revisaoprovapdm2.databinding.FragmentGalleryBinding;

import java.util.Locale;

public class GalleryFragment extends Fragment implements TextToSpeech.OnInitListener {

    private FragmentGalleryBinding binding;
    private TextToSpeech textToSpeech;
    private String msg;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        msg = galleryViewModel.obterAlunosAprovados(getContext());
        if (msg == null || msg.isEmpty()) {
            msg = "Nenhum aluno aprovado.";
        }

        textToSpeech = new TextToSpeech(getActivity(), this);


        final TextView textView = binding.textGallery;
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            Locale locale = new Locale("pt", "BR");
            int result = textToSpeech.setLanguage(locale);
            textToSpeech.setSpeechRate(0.8f);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {

                Toast.makeText(getContext(),
                        "Linguagem não suportada",
                        Toast.LENGTH_LONG).show();

            } else {

                textToSpeech.setLanguage(new Locale("pt", "BR"));
                textToSpeech.setSpeechRate(0.8f);
                textToSpeech.speak(msg,
                        TextToSpeech.QUEUE_FLUSH,
                        null,
                        "ID1");
            }

        } else {
            Toast.makeText(getContext(),
                    "Falha na inicialização",
                    Toast.LENGTH_LONG).show();
        }
    }
}