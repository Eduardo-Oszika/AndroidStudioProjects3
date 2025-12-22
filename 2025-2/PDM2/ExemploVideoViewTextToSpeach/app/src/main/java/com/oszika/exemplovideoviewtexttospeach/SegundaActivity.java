package com.oszika.exemplovideoviewtexttospeach;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class SegundaActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private VideoView videoView;
    private Button buttonPlay, buttonPause, buttonStop;
    private Uri uri;
    private TextToSpeech textToSpeech;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_segunda);

        String nome = getIntent().getStringExtra("nome");
        msg = "Seja bem vindo " + nome + " Seu video está carregado e pronto para ser reproduzido";
        textToSpeech = new TextToSpeech(SegundaActivity.this, this);
        videoView = findViewById(R.id.videoView);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonPause = findViewById(R.id.buttonPause);
        buttonStop = findViewById(R.id.buttonStop);
        uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);
        videoView.setVideoURI(uri);
    }


    public void playButton(View view) {

        videoView.start();
        int duracao = videoView.getDuration();
        Toast.makeText(this, "Duração do vídeo: " + duracao + " milissegundos", Toast.LENGTH_LONG).show();
    }

    public void pauseButton(View view) {
        videoView.pause();
        int posicao = videoView.getCurrentPosition();
        Toast.makeText(this, "Posição atual do vídeo: " + posicao + " milissegundos", Toast.LENGTH_LONG).show();
    }

    public void stopButton(View view) {
        videoView.stopPlayback();
        Toast.makeText(this, "Vídeo parado", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            Locale locale = new Locale("pt", "BR");
            int result = textToSpeech.setLanguage(locale);
            textToSpeech.setSpeechRate(0.8f);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "Linguagem não suportada", Toast.LENGTH_LONG).show();
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    textToSpeech.speak(msg, TextToSpeech.QUEUE_FLUSH, null, "ID1");
                } else {
                    textToSpeech.speak(msg, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        } else {
            Toast.makeText(this, "Falha na inicialização do TextToSpeech", Toast.LENGTH_LONG).show();
        }
    }
}