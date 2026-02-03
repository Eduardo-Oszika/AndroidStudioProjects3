package com.oszika.provapdm2v2.util.notification;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationHelper {
    private static final String CANAL_ID = "2";
    private static final int NOTIFICACAO_ID = 1;
    public static void criarCanal(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence nome = "canal1";
            String descricao = "descrição do canal 1";
            int importancia = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel canal = new NotificationChannel(CANAL_ID, nome, importancia);
            canal.setDescription(descricao);
            NotificationManager nm = context.getSystemService(NotificationManager.class);
            nm.createNotificationChannel(canal);
        }
    }
    public static void gerarNotificacao(Context context, String titulo, String conteudo) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
                        != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CANAL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(titulo)
                .setContentText(conteudo)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(NOTIFICACAO_ID, builder.build());
    }
}