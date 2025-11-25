package com.oszika.exemplohandlermensager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView textViewNumber;
    private Button button;
    private Handler handler;
    private static final String TAG = "Thread";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textViewNumber = findViewById(R.id.textViewNumber);
        button = findViewById(R.id.buttonClicar);
        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                textViewNumber.setText(String.valueOf(msg.arg1));
                Log.i(TAG, Thread.currentThread().getName());
            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicar();
            }
        });
    }

    public void clicar() {
        button.setEnabled(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                for (int i = 0; i < 10; i++) {
                    int num = random.nextInt(21);
                    Message msg = handler.obtainMessage();
                    msg.arg1 = num;
                    handler.sendMessage(msg);

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}