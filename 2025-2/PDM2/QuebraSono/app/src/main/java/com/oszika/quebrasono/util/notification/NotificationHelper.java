package com.oszika.quebrasono.util.notification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.oszika.quebrasono.R;
import com.oszika.quebrasono.activity.resultado.ResultadoActivity;
import com.oszika.quebrasono.util.service.MusicService;

public class NotificationHelper {

    public static final String CHANNEL_ID = "canal_1";
    public static final int NOTIFICATION_ID = 1;
    private Context context;

    public NotificationHelper(Context context) {
        this.context = context;
    }

    private void criarCanal() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence nome = "canal 1";
            String descricao = "Canal descricao 1";
            int importancia = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel canal = new NotificationChannel(CHANNEL_ID, nome, importancia);
            canal.setDescription(descricao);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(canal);
        }
    }

    @SuppressLint("MissingPermission")
    public void gerarNotificacao(String titulo, String mensagem) {
        criarCanal();

        Intent it = new Intent(context, ResultadoActivity.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pi = PendingIntent.getActivity(context, 0, it, PendingIntent.FLAG_IMMUTABLE);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.planeta_terra);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.planeta_terra)
                .setContentTitle(titulo)
                .setContentText(mensagem)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setLargeIcon(bitmap)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Alarme disparado! "));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }

    public static Notification gerarNotificacaoMusica(Context context) {
        String musicChannel = "music_channel";
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    musicChannel,
                    "Music Service",
                    NotificationManager.IMPORTANCE_LOW
            );
            manager.createNotificationChannel(channel);
        }
        return new NotificationCompat.Builder(context, musicChannel)
                .setContentTitle("Reproduzindo música")
                .setContentText("A música está tocando em segundo plano")
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setOngoing(true) // notificação persistente
                .build();
    }
}
