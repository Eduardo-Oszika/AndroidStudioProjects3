package com.oszika.exemplonotificacaoexpansivel;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_NOTIFICACAO = 100;
    private PermissionHelper permissionHelper;
    private NotificationHelper notificationHelper;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        permissionHelper = new PermissionHelper(this);
        notificationHelper = new NotificationHelper(this);
        button = findViewById(R.id.buttonGerar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (permissionHelper.temPermissao()){
                    //notificationHelper.gerarNotificacao("Titulo teste", "Mensagem teste");
                    notificationHelper.gerarNotificacaoPersonalizada();
                } else {
                    permissionHelper.solicitarPermissao(REQUEST_NOTIFICACAO);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_NOTIFICACAO) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                notificationHelper.gerarNotificacao("Titulo teste", "Mensagem teste");
                    notificationHelper.gerarNotificacaoPersonalizada();
            }
        }
    }
}