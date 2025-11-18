package com.oszika.provapdm1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.oszika.provapdm1.entity.Rodada;
import com.oszika.provapdm1.entity.Usuario;

import java.util.ArrayList;
import java.util.List;

public class AcertosActivity extends AppCompatActivity {
    private ListView listView;


    private TextView tvAcertos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_acertos);
        tvAcertos = findViewById(R.id.tv_numero_de_acertos);
        Usuario user = getIntent().getSerializableExtra("user", Usuario.class);
        List<String> listRodadas = new ArrayList<>();
        if (user != null) {
            int acertos = 0;
            for (int i = 0; i < user.getRodadas().size(); i++) {
                if (user.getRodadas().get(i).getAcertou() == true) {
                    acertos++;
                }
                int index = i +1;
                listRodadas.add("Rodada "+index+ ": " +  (user.getRodadas().get(i).getAcertou() ? "Acertou" : "Errou"));
            }


            tvAcertos.setText("Numero de acertos: " + acertos);
        }


        listView = findViewById(R.id.lvRodadas);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listRodadas);
        listView.setAdapter(adapter);
    }

}