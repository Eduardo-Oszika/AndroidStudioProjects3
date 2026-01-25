package com.oszika.provapdm1v2;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import com.oszika.provapdm1v2.dao.AppDao;
import com.oszika.provapdm1v2.dao.AppDatabase;
import com.oszika.provapdm1v2.entity.Pergunta;
import com.oszika.provapdm1v2.entity.Usuario;
import com.oszika.provapdm1v2.fragments.SelecaoLoginFragment;
import com.oszika.provapdm1v2.service.ServicePergunta;
import com.oszika.provapdm1v2.service.ServiceUsuario;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    public AppDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "pergunta.db").allowMainThreadQueries().build();
        dao = db.appDao();
        inserirDadosIniciais();

        if (savedInstanceState == null) {
            mostrar(new SelecaoLoginFragment());

        }
    }

    public void mostrar(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);

        ft.commit();
    }


    private void inserirDadosIniciais() {
        ServiceUsuario serviceUsuario = new ServiceUsuario(dao);
        serviceUsuario.insertUsuarioDefaults();

        ServicePergunta servicePergunta = new ServicePergunta(dao);
        servicePergunta.insertPerguntaDefaults();

    }

    public AppDao getDao() {
        return dao;
    }
}