package com.oszika.quebrasono.util.permission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.oszika.quebrasono.R;

public class PermissionHelper {
    private AppCompatActivity activity;

    public PermissionHelper(AppCompatActivity activity) {
        this.activity = activity;
    }

    public boolean temPermissao() {
        int status = ContextCompat.checkSelfPermission(activity, Manifest.permission.POST_NOTIFICATIONS);
        return status == PackageManager.PERMISSION_GRANTED;
    }

    public void solicitarPermissao(int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.POST_NOTIFICATIONS)) {
            Toast.makeText(activity, R.string.a_permissao_necessaria, Toast.LENGTH_SHORT).show();
        }

        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.POST_NOTIFICATIONS}, requestCode);
    }
}
