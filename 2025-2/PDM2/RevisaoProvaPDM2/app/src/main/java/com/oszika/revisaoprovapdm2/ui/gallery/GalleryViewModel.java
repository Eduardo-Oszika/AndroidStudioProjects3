package com.oszika.revisaoprovapdm2.ui.gallery;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.oszika.revisaoprovapdm2.entity.Resultado;

import java.util.ArrayList;
import java.util.List;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private static final String AUTHORITY = "com.oszika.revisaoprovapdm2.provider";
    private static final String PATH_RESULTADO = "resultado";
    private static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + PATH_RESULTADO);

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public MutableLiveData<String> getmText() {
        return mText;
    }

    public String obterAlunosAprovados(Context context) {
        StringBuilder aprovados = new StringBuilder();


        Cursor cursor = context.getContentResolver().query(CONTENT_URI, null, null, null, null);

        aprovados.append("Alunos Aprovados: ");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Resultado r;
                long id = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
                String disciplina = cursor.getString(cursor.getColumnIndexOrThrow("disciplina_nome"));
                String nomeAluno = cursor.getString(cursor.getColumnIndexOrThrow("aluno_nome"));
                float notaFinal = cursor.getFloat(cursor.getColumnIndexOrThrow("aluno_nota_final"));
                String status = cursor.getString(cursor.getColumnIndexOrThrow("aluno_status"));


                r= new Resultado(id,disciplina,nomeAluno, notaFinal, status);
                if (r.status.equals("Aprovado")) {
                    aprovados.append(r.nomeAluno).append(", ");
                }
            }
            cursor.close();
        }

        return aprovados.toString();
    }
}