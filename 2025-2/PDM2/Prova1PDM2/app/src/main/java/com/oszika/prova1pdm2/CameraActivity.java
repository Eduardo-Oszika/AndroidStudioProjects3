package com.oszika.prova1pdm2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import com.oszika.prova1pdm2.util.ManipulaArquivo;

import java.io.IOException;
import java.text.Format;

public class CameraActivity extends AppCompatActivity {
    private ManipulaArquivo arquivo;
    private ImageView imageView;
    private final ActivityResultLauncher<Intent> cameraLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                                Intent data = result.getData();
                                Bitmap foto = null;
                                if (Build.VERSION.SDK_INT >= 33) {
                                    foto = data.getParcelableExtra("data", Bitmap.class);
                                    salvarFoto(foto);
                                } else {
                                    foto = (Bitmap) data.getParcelableExtra("data");
                                    salvarFoto(foto);
                                }
                                if (foto != null) {
                                    imageView.setImageBitmap(foto);
                                }
                            }
                        }
                    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        imageView = findViewById(R.id.imageViewFoto);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraLauncher.launch(intent);
        arquivo = new ManipulaArquivo(this);
    }

    private void salvarFoto(Bitmap foto) {

        try {
            arquivo.salvarArquivo("meuArq.png", foto,true);
            Toast.makeText(this, "Texto salvo com sucesso", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Erro ao salvar o arquivo", Toast.LENGTH_SHORT).show();
        }
    }
}