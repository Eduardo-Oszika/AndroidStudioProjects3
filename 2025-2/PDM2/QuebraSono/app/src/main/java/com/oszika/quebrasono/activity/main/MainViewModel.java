package com.oszika.quebrasono.activity.main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Build;
import android.os.SystemClock;
import android.widget.TimePicker;

import androidx.lifecycle.ViewModel;

import java.util.Calendar;

public class MainViewModel extends ViewModel {

    private AlarmManager alarmManager;
    public Long marcarAlarme(TimePicker timePicker, PendingIntent pendingIntent){
        int hora=0, minuto=0;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            hora = timePicker.getHour();
            minuto = timePicker.getMinute();
        }//if
        else{
            hora = timePicker.getCurrentHour();
            minuto = timePicker.getCurrentMinute();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hora);
        calendar.set(Calendar.MINUTE, minuto);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 1);
        }

        return calendar.getTimeInMillis();
    }

}
