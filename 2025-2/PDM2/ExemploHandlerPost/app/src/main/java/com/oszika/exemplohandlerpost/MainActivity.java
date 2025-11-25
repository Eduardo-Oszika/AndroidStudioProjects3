package com.oszika.exemplohandlerpost;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView textViewNumber;
    private Button buttonClicar;
    private Handler handler;
    private Integer numberG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewNumber = findViewById(R.id.textViewNumber);
        buttonClicar = findViewById(R.id.buttonClicar);
        handler = new Handler(getMainLooper());

    }

    public void clicar(View view) {
        if (view.getId() == R.id.buttonClicar) {
            Log.i("Main", "Thread atual: "
                    + Thread.currentThread());

            Random random = new Random();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        Log.i("Thread", "Thread em execução: "
                                + Thread.currentThread());

                        numberG = random.nextInt(21); // número aleatório 0-20

                        try {
                            Thread.sleep(2000); // 2 segundos (ajustei, antes eram 10s muito longos)
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.i("Post", "Thread que atualiza UI: " + Thread.currentThread());
                                textViewNumber.setText(String.valueOf(numberG));
                            }
                        });
                    }
                }
            }).start();
        }
    }
}
