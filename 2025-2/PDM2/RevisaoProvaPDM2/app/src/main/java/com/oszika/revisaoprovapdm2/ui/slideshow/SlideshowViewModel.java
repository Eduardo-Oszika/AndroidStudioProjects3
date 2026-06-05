package com.oszika.revisaoprovapdm2.ui.slideshow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oszika.revisaoprovapdm2.entity.Resultado;
import com.oszika.revisaoprovapdm2.pojo.AlunoPojo;
import com.oszika.revisaoprovapdm2.pojo.DisciplinaPojo;
import com.oszika.revisaoprovapdm2.util.Conexao;
import com.oszika.revisaoprovapdm2.util.Conversao;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SlideshowViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<AlunoPojo>> alunoslista;
    private final MutableLiveData<ArrayList<Resultado>> lista;
    private DisciplinaPojo disciplinaPojo;
    private final String URL = "https://eduardo-oszika.github.io/AndroidStudioProjects3/professor.json";
    private ExecutorService executorService;
    private Handler mainHandler;
    private final MutableLiveData<String> erro;
    private final MutableLiveData<Boolean> cadastroSucesso;

    private static final String AUTHORITY = "com.oszika.revisaoprovapdm2.provider";
    private static final String PATH_RESULTADO = "resultado";
    private static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + PATH_RESULTADO);


    public SlideshowViewModel() {
        lista = new MutableLiveData<>();
        alunoslista = new MutableLiveData<>();
        executorService = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());
        erro = new MutableLiveData<>();
        cadastroSucesso = new MutableLiveData<>();
        obterDados();
    }

    public MutableLiveData<ArrayList<AlunoPojo>> getAlunos() {
        return alunoslista;
    }

    public MutableLiveData<ArrayList<Resultado>> getResultado() {
        return lista;
    }

    public MutableLiveData<String> getErro() {
        return erro;
    }

    public MutableLiveData<Boolean> getCadastroSucesso() {
        return cadastroSucesso;
    }

    void converterResultados() {
        if (disciplinaPojo != null && disciplinaPojo.getAlunos() != null) {
            ArrayList<Resultado> resultados = new ArrayList<>();
            for (AlunoPojo aluno : disciplinaPojo.getAlunos()) {
                resultados.add(new Resultado(disciplinaPojo.getDisciplina(), aluno.getNome(), aluno.getNotaFinal(), aluno.getStatus()));
            }
            lista.setValue(resultados);

        }

    }

    private void obterDados() {
        executorService.execute(() -> {
            try {
                Conexao conexao = new Conexao();
                InputStream inputStream = conexao.obterRespostaHTTP(URL);
                Conversao conversao = new Conversao();
                String textoJSON = conversao.converter(inputStream);
                inputStream.close();
                Log.i("JSON", "doInBackground: " + textoJSON);
                if (textoJSON != null) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<DisciplinaPojo>() {
                    }.getType();
                    disciplinaPojo = gson.fromJson(textoJSON, type);

                }
                mainHandler.post(() -> {
                    if (!disciplinaPojo.getAlunos().isEmpty()) {
                        ArrayList<AlunoPojo> alunos = new ArrayList<>(disciplinaPojo.getAlunos());
                        alunoslista.setValue(alunos);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void salvarResultados(Context context, ArrayList<Resultado> resultados) {

        Cursor cursor = context.getContentResolver().query(CONTENT_URI, null, null, null, null);

        ArrayList<Resultado> resultadosExistentes = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Resultado r;
                long id = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
                String disciplina = cursor.getString(cursor.getColumnIndexOrThrow("disciplina_nome"));
                String nomeAluno = cursor.getString(cursor.getColumnIndexOrThrow("aluno_nome"));
                float notaFinal = cursor.getFloat(cursor.getColumnIndexOrThrow("aluno_nota_final"));
                String status = cursor.getString(cursor.getColumnIndexOrThrow("aluno_status"));
                r = new Resultado(id, disciplina, nomeAluno, notaFinal, status);
                resultadosExistentes.add(r);
            }
            cursor.close();
        }

        if (resultadosExistentes.isEmpty()) {
            int count = 0;
            for (Resultado resultado : resultados) {
                ContentValues values = new ContentValues();
                values.put("disciplina_nome", resultado.disciplina);
                values.put("aluno_nome", resultado.nomeAluno);
                values.put("aluno_nota_final", resultado.notaFinal);
                values.put("aluno_status", resultado.status);

                Uri uri = context.getContentResolver().insert(CONTENT_URI, values);

                if (uri != null) {
                    count++;
                }
            }
            if (count == resultados.size()) {
                cadastroSucesso.setValue(true);
            } else {
                erro.setValue("Erro ao salvar resultados");
            }
        }else
            cadastroSucesso.setValue(true);
    }
}