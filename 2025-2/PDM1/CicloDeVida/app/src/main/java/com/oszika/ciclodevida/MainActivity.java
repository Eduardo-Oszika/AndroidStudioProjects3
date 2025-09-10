package com.oszika.ciclodevida;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private Button buttonHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MAIN", "onCreate");
        buttonHello = findViewById(R.id.buttonHello);
        buttonHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, SegundaActivity.class);
                startActivity(it);
            }
        });
    }//onCreate


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MAIN", "onStart");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MAIN", "onStop");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MAIN", "onDestroy");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MAIN", "onPause");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MAIN", "onResume");
    }
}//class
