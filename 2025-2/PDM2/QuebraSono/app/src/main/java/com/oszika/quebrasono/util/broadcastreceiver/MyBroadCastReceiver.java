package com.oszika.quebrasono.util.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.oszika.quebrasono.util.notification.NotificationHelper;

public class MyBroadCastReceiver extends BroadcastReceiver {
    private String TAG = "MyBroadCastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        notificationHelper.gerarNotificacao("Quebra Sono", "It's time to take a break!");
        Log.d(TAG, "onReceive: ");

    }
}
