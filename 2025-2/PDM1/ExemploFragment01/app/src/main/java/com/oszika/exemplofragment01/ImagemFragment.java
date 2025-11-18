package com.oszika.exemplofragment01;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ImagemFragment extends Fragment {


    public ImagemFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("Frag","anexado a activity");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Frag","view sendo criada");
        return inflater.inflate(R.layout.fragment_imagem,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Frag","view criada");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Frag","inicializado");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Frag","pronto para atender o usuário");
    }



    @Override
    public void onPause() {
        super.onPause();
        Log.d("Frag","pausado");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Frag","parado");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Frag","frag destruído");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Frag","view destruida");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Frag","desanexado com a activity");
    }
}//class
