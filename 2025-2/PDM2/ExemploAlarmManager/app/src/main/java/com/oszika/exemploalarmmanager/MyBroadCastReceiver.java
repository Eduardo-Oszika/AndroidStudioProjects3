package com.oszika.exemploalarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Integer num = intent.getIntExtra("numero", 0);
        Log.d("TAG", "Número recebido no BroadcastReceiver: " + num);

    }
}
