package com.oszika.exemplotoolbarmenu;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    public Resources getResources() {
        return super.getResources();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.meu_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("APPBAR");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent it;
        if (R.id.java == item.getItemId()){
            it = new Intent(this, JavaActivity.class);
            it.putExtra("dados","JAVA");
            startActivity(it);
            return true;
        }
        if (R.id.php == item.getItemId()){
            Toast.makeText(this, "PHP", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (R.id.c == item.getItemId()){
            Toast.makeText(this, "C", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (android.R.id.home == item.getItemId()){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}