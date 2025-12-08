package com.oszika.quebrasono.activity.main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.oszika.quebrasono.R;
import com.oszika.quebrasono.util.broadcastreceiver.MyBroadCastReceiver;
import com.oszika.quebrasono.util.permission.PermissionHelper;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_NOTIFICACAO = 100;
    private TimePicker timePicker;
    private Button button;
    private MainViewModel viewModel;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private PermissionHelper permissionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        button = findViewById(R.id.button);
        permissionHelper = new PermissionHelper(this);

        if (!permissionHelper.temPermissao()) {
            permissionHelper.solicitarPermissao(REQUEST_NOTIFICACAO);
        }


        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent i = new Intent(this, MyBroadCastReceiver.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        pendingIntent = PendingIntent.getBroadcast(this, 0, i,
                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (permissionHelper.temPermissao()) {
                    //notificationHelper.gerarNotificacao("Titulo teste", "Mensagem teste");
                    Long time = viewModel.marcarAlarme(timePicker, pendingIntent);
                    try {
                        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingIntent);
                    } catch (SecurityException e) {
                        throw new RuntimeException(e);
                    }
                    Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
                } else {

                    permissionHelper.solicitarPermissao(REQUEST_NOTIFICACAO);
                }

            }
        });
    }//onCreate

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_NOTIFICACAO) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            }
//        }
//    }
}//class