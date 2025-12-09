package com.oszika.exemplogmail;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button buttonGmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        buttonGmail = findViewById(R.id.buttonGmail);

    }

    public void gMAIL(View view) {
        String[] email = {"eduardoozika.ti@gmail.com"};
        String titulo = "Titulo";
        String mensagem = "Mensagem de teste do Gmail";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, email);
        intent.putExtra(Intent.EXTRA_SUBJECT, titulo);
        intent.putExtra(Intent.EXTRA_TEXT, mensagem);
        intent.setPackage("com.google.android.gm");
        startActivity(intent);
    }

    public void wHATSSAPP(View view) {
        String text = "Hello Word";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,text);
        intent.setPackage("com.whatsapp");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Intent playStoreIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp"));
            startActivity(playStoreIntent);
        }
    }
}