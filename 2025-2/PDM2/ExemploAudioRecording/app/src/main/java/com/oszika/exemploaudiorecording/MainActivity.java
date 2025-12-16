package com.oszika.exemploaudiorecording;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.oszika.exemploaudiorecording.databinding.ActivityMainBinding;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String AUDIO_FILE = "audiorecordtest.m4a";
    private ActivityMainBinding binding;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private boolean isRecording = false;
    private boolean isPlaying = false;
    private String audioFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        audioFilePath = getExternalFilesDir(null) + "/" + AUDIO_FILE;
        verificarSolitarPermissao();
        limparButtons();
        alterarButtonText();


    }

    private void alterarButtonText() {
        if (isRecording){
            binding.recordButton.setText("Parar Gravação");
        }
        else {
            binding.recordButton.setText("Iniciar Gravação");
        }

        if (isPlaying){
            binding.playButton.setText("Parar Reprodução");
        }
        else {
            binding.playButton.setText("Iniciar Reprodução");
        }
    }

    private void limparButtons() {
        binding.recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isRecording){
                    stopRecording();
                }
                else {
                    startRecording();
                }
                isRecording = !isRecording;
                alterarButtonText();
            }
        });
        binding.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlaying){
                    stopPlaying();
                }
                else {
                    startPlaying();
                }
                isPlaying = !isPlaying;
                alterarButtonText();
            }
        });
    }

    private void startPlaying() {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(audioFilePath);
            mediaPlayer.prepare();
            mediaPlayer.start();
            Toast.makeText(this, "Reprodução iniciada", Toast.LENGTH_SHORT).show();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopPlaying();
                    isPlaying = false;
                    alterarButtonText();
                    Toast.makeText(MainActivity.this, "Reprodução finalizada", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            Log.e(TAG,"erro na reprodução", e);
            isPlaying = false;
            alterarButtonText();
        }
    }

    private void stopPlaying() {
        if (mediaPlayer != null){
            if (mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopRecording();
        stopPlaying();
    }

    private void startRecording() {
        mediaRecorder = new MediaRecorder(this);
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setOutputFile(audioFilePath);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mediaRecorder.setAudioSamplingRate(44100);
        mediaRecorder.setAudioEncodingBitRate(192000);
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
            Toast.makeText(this, "Gravação iniciada", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e(TAG,"erro na gravação", e);
            isRecording = false;
            alterarButtonText();
        }
    }

    private void stopRecording() {
        if (mediaRecorder!= null){
            mediaRecorder.stop();
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
            Toast.makeText(this, "Gravação finalizada", Toast.LENGTH_SHORT).show();
        }

    }

    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean o) {
                    if (o)
                        Toast.makeText(MainActivity.this, "Permissão Liberada", Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(MainActivity.this, "Permissão Negada", Toast.LENGTH_SHORT).show();
                        disabibilitarBotoes();
                    }
                }
            });

    private void disabibilitarBotoes() {
        binding.recordButton.setEnabled(false);
        binding.playButton.setEnabled(false);
    }//


    private void verificarSolitarPermissao() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO);

        }
    }
}