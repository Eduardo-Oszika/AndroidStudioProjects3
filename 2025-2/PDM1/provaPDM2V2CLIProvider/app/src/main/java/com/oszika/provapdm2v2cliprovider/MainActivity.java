package com.oszika.provapdm2v2cliprovider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private SimpleCursorAdapter adapter;
    private Button btnMostrar;


    private long selectedId = -1;

    private static final String AUTHORITY = "com.oszika.provapdm2v2.provider";
    public static final String PATH = "jogadas";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    public static final String ID = "_id";
    public static final String NOME = "jogada_nome";
    public static final String RESULTADO = "resultado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btnMostrar = findViewById(R.id.btnMostrar);
        listView = findViewById(R.id.lista);

        String[] colunas = new String[]{NOME, RESULTADO};
        int[] views = new int[]{android.R.id.text1, android.R.id.text2};
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, null, colunas, views, 0);
        listView.setAdapter(adapter);

        btnMostrar.setOnClickListener(v -> atualizarLista());

    }

    private void atualizarLista() {
        Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, null);
        adapter.swapCursor(cursor);
        adapter.notifyDataSetChanged();
    }
}