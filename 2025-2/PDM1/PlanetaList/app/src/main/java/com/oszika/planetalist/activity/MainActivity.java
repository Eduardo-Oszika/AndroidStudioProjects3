package com.oszika.planetalist.activity;

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
import androidx.room.Room;

import com.oszika.planetalist.R;
import com.oszika.planetalist.database.AppDAO;
import com.oszika.planetalist.database.AppDatabase;
import com.oszika.planetalist.database.InstanciaDB;
import com.oszika.planetalist.fragment.PlanetListFragment;

public class MainActivity extends AppCompatActivity {
    private AppDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.meu_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        AppDatabase db = InstanciaDB.getInstance(getApplicationContext());
        dao = db.appDAO();

        if (savedInstanceState == null) {
            mostrar(new PlanetListFragment());
        }

    }

    private void mostrar(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent it = new Intent(this, DetailActivity.class);
        if (item.getItemId() == R.id.sol) {
            it.putExtra("nome", "Sol");
            it.putExtra("temperatura", "Temperatura media:  5504,85 °C");
            it.putExtra("distancia", "Distancia media: 149,6 milhões km da Terra");
        }
        if (item.getItemId() == R.id.lua) {
            it.putExtra("nome", "Lua");
            it.putExtra("temperatura", "Temperatura media: -153°C durante à noite a 107°C durante o dia");
            it.putExtra("distancia", "Distancia media: 384,400 km da Terra");
        }
        startActivity(it);
        return super.onOptionsItemSelected(item);
    }
}