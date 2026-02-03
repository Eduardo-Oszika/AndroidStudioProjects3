package com.oszika.provapdm2v2.util.permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class PermissionHelper {
    private final AppCompatActivity activity;
    public static final int REQUEST_NOTIFICACAO = 101;

    public PermissionHelper(AppCompatActivity activity) {
        this.activity = activity;
    }
    public boolean temPermissaoNotificacao(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return ActivityCompat.checkSelfPermission(activity,
                    Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    public void pedirPermissaoNotificacao(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (!temPermissaoNotificacao(activity)) {
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        REQUEST_NOTIFICACAO);
            }
        }
    }
}