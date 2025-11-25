package com.oszika.exemploarquivointerno;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ManipularArquivo {
    private Context context;

    public ManipularArquivo(Context context) {
        this.context = context;
    }

    public void salvarArquivo(String nome, String conteudo, boolean append) {
        File arquivo = new File(context.getFilesDir(), nome);
        try {
            FileOutputStream fos = new FileOutputStream(arquivo, append);
            fos.write(conteudo.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String lerArquivo(String nome) {
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream fis = context.openFileInput(nome);
            int letra = 0;
            while ((letra = fis.read()) != -1) {
                sb.append((char) letra);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void deletarArquivo(String nome){
        context.deleteFile(nome);
    }//method

}
