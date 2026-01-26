package com.oszika.provapdm2v2.ui.home;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oszika.provapdm2v2.MainActivity;
import com.oszika.provapdm2v2.dao.AppDao;
import com.oszika.provapdm2v2.dao.AppDatabase;
import com.oszika.provapdm2v2.ui.entity.PersonagemPojo;
import com.oszika.provapdm2v2.ui.entity.Personagem;
import com.oszika.provapdm2v2.ui.entity.Personagens;
import com.oszika.provapdm2v2.util.Conexao;
import com.oszika.provapdm2v2.util.Conversao;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<PersonagemPojo>> lista;
    private final String URL = "https://eduardo-oszika.github.io/AndroidStudioProjects3/personagens-disney.json";
    private ExecutorService executorService;
    private Handler mainHandler;
    private Personagens personagensContainer;
    private AppDao dao;


    public HomeViewModel() {

        lista = new MutableLiveData<>();
        executorService = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());
        obterDados();
    }



    public void criarBanco(Context context) {
        AppDatabase db = AppDatabase.getDatabase(context.getApplicationContext());
        dao = db.appDao();
        if (dao.obterPersonagems().isEmpty()) {
            List<PersonagemPojo> personagens = lista.getValue();
            if (personagens != null) {
                for (PersonagemPojo p : personagens) {
                    dao.insertPersonagem(new Personagem(p.getId(),p.getName(),p.getImageUrl()));
                }
            }
        }
    }


    public MutableLiveData<ArrayList<PersonagemPojo>> getPersonagens() {

        return lista;
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
                    Type type = new TypeToken<Personagens>() {
                    }.getType();
                    personagensContainer = gson.fromJson(textoJSON, type);

                }
                mainHandler.post(() -> {
                    if (!personagensContainer.getData().isEmpty()) {
                        // Atualize os LiveData ou execute outras ações na thread principal
                        ArrayList<PersonagemPojo> personagensList = new ArrayList<>(personagensContainer.getData());
                        lista.setValue(personagensList);


                    } else {
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}