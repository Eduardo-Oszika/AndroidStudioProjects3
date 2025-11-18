package com.oszika.prova1pdm2.util;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ManipulaArquivo {
    private final Context context;
    public ManipulaArquivo(Context context) {
        this.context = context;
    }
    public void salvarArquivo(String nome, Bitmap conteudo, boolean append) throws IOException {
        File arquivo = new File(context.getFilesDir(), nome);
        try (FileOutputStream fos = new FileOutputStream(arquivo, append)) {
            fos.write(conteudo.compress(Bitmap.CompressFormat.PNG, 100, fos) ? 1 : 0);
        }
    }
    public String lerArquivo(String nome) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (FileInputStream fis = context.openFileInput(nome)) {
            int letra;
            while ((letra = fis.read()) != -1) {
                sb.append((char) letra);
            }
        }
        return sb.toString();
    }
    public void deletarArquivo(String nome) {
        context.deleteFile(nome);
    }
}//class
