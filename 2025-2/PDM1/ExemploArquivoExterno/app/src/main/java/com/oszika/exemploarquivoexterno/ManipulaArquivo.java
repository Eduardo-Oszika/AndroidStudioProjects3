package com.oszika.exemploarquivoexterno;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ManipulaArquivo {
    private final Context context;

    public ManipulaArquivo(Context context) {
        this.context = context;
    }

    private File obterArquivo(String nome) {
        File dir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        return new File(dir, nome);
    }

    public void salvarArquivo(String nome, String conteudo) throws IOException {
        File arquivo = obterArquivo(nome);
        FileOutputStream fos = new FileOutputStream(arquivo);
        fos.write(conteudo.getBytes());

    }

    public String lerArquivo(String nome) throws IOException {
        File arquivo = obterArquivo(nome);
        StringBuilder conteudo = new StringBuilder();
        FileInputStream fis = new FileInputStream(arquivo);
        int letra = 0;
        while ((letra = fis.read()) != -1) {
            conteudo.append((char) letra);
        }
        return conteudo.toString();
    }
    public void deletarArquivo(String nome) {
        File arquivo = obterArquivo(nome);
        if (arquivo.exists()) {
            arquivo.delete();
        }
    }
}
