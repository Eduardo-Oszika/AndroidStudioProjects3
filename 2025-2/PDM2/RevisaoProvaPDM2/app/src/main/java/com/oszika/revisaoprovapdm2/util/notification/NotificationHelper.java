package com.oszika.revisaoprovapdm2.util.notification;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.oszika.revisaoprovapdm2.MainActivity;
import com.oszika.revisaoprovapdm2.ui.gallery.GalleryFragment;

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
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(canal);
        }
    }
    public static void gerarNotificacao(Context context, String titulo, String conteudo) {
        criarCanal(context);

        Intent it = new Intent(context, MainActivity.class);
        it.putExtra("abrir_gallery", true);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pi = PendingIntent.getActivity(context, 0, it, PendingIntent.FLAG_IMMUTABLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
                        != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CANAL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(titulo)
                .setContentText(conteudo)
                .setContentIntent(pi)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(NOTIFICACAO_ID, builder.build());
    }
}