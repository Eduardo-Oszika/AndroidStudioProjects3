package com.oszika.exemplonav01;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerMain);
        navigationView = findViewById(R.id.navigation_01);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selecionado = null;
                int id = item.getItemId();
                if (id == R.id.nav_flamengo) {
                    selecionado = new FlamengoFragment();
                } else if (id == R.id.nav_corinthias) {
                    selecionado = new CorinthiasFragment();
                } else if (id == R.id.nav_goias) {
                    selecionado = new GoiasFragment();
                }
                if (selecionado != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fl_conteiner, selecionado)
                            .commit();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }

        });

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_conteiner, new FlamengoFragment())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_flamengo);
        }

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    finish();
                }
            }
        });
    }
}