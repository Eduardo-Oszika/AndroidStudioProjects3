package com.oszika.exemplofragment01;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    private boolean controleFragment = true;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Log.e("Activity", "Criada");
        if (savedInstanceState == null) {
            mostrar(new ImagemFragment());

        }
        btn = findViewById(R.id.button);
        btn.setOnClickListener(view -> alterarFragment());

    }

    private void alterarFragment() {
        if (controleFragment) {
            mostrar(new ListaFragment());
        } else {
            mostrar(new ImagemFragment());
        }
        controleFragment = !controleFragment;

    }

    private void mostrar(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);

        ft.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Activity","parada");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Activity","inicializada");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Activity","destruida");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Activity","pausada");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Activity","pronta");
    }
}//class