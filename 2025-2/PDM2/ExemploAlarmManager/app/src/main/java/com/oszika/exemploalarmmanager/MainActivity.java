package com.oszika.exemploalarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Integer numeroGerado;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        button.setOnClickListener(view -> dispararAlarme2());

    }

    private void dispararAlarme() {
        numeroGerado = gerarNumero();
        Toast.makeText(this, numeroGerado.toString(), Toast.LENGTH_SHORT).show();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent i = new Intent(this, MyBroadCastReceiver.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.putExtra("numero", numeroGerado);
        pendingIntent = PendingIntent.getBroadcast(this, 0, i,
                PendingIntent.FLAG_IMMUTABLE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 60 * 1000, pendingIntent);

    }

    private void dispararAlarme2() {
        numeroGerado = gerarNumero();
        Toast.makeText(this, numeroGerado.toString(), Toast.LENGTH_SHORT).show();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent i = new Intent(this, MyBroadCastReceiver.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.putExtra("numero", numeroGerado);
        pendingIntent = PendingIntent.getBroadcast(this, 0, i,
                PendingIntent.FLAG_IMMUTABLE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 21);
        calendar.set(Calendar.MINUTE, 50);
        alarmManager.set(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis() + 1000, pendingIntent);
    }//


    private Integer gerarNumero() {
        Random random = new Random();
        return random.nextInt(10);
    }
}