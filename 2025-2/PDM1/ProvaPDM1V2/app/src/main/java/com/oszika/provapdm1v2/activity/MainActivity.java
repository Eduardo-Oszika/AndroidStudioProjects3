package com.oszika.provapdm1v2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.oszika.provapdm1v2.R;
import com.oszika.provapdm1v2.entity.Usuario;
import com.oszika.provapdm1v2.fragments.SelecaoLoginFragment;
import com.oszika.provapdm1v2.service.ServicePergunta;
import com.oszika.provapdm1v2.service.ServiceUsuario;

public class MainActivity extends AppCompatActivity {
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.meu_toolbar);
        setSupportActionBar(toolbar);


        inserirDadosIniciais();

        if (savedInstanceState == null) {
            mostrar(new SelecaoLoginFragment());

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent it;
        if (R.id.pontotal == item.getItemId()) {
            it = new Intent(this, PontuacaoTotal.class);
            startActivity(it);
            return true;
        }
        if (R.id.detalhe == item.getItemId()) {
            it = new Intent(this, QuestaoDetalhe.class);
            startActivity(it);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void mostrar(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);

        ft.commit();
    }


    private void inserirDadosIniciais() {
        ServiceUsuario serviceUsuario = new ServiceUsuario(getApplicationContext());
        serviceUsuario.insertUsuarioDefaults();

        ServicePergunta servicePergunta = new ServicePergunta(getApplicationContext());
        servicePergunta.insertPerguntaDefaults();

    }

    public void setUsuarioLogado(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioLogado() {
        return usuario;
    }
}