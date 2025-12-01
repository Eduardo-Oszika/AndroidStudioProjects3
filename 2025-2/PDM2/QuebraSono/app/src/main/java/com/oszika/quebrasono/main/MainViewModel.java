package com.oszika.quebrasono.main;

import android.app.AlarmManager;
import android.os.Build;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private AlarmManager alarmManager;



    public void obterHora(TimePicker timePicker){
        int hora=0, minuto=0;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            hora = timePicker.getHour();
            minuto = timePicker.getMinute();
        }//if
        else{
            hora = timePicker.getCurrentHour();
            minuto = timePicker.getCurrentMinute();
        }
        String tempo = hora+"/" +minuto;
    }
}
