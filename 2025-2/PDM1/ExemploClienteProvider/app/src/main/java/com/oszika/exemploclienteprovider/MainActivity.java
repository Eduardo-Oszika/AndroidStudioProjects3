package com.oszika.exemploclienteprovider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private SimpleCursorAdapter adapter;
    private static final String AUTHORITY = "com.example.pc_02.DadosProvider";
    private static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/dados");
    private Button buttonMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lista);
        buttonMostrar = findViewById(R.id.btnMostrar);

        String[] colunas = new String[]{"nome", "telefone"};
        int[] views = new int[]{android.R.id.text1, android.R.id.text2};
        adapter = new SimpleCursorAdapter(MainActivity.this,
                android.R.layout.simple_list_item_2,
                null, colunas, views, 0);
        listView.setAdapter(adapter);

        buttonMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizar();
            }
        });
    }//onCreate

    private void atualizar() {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
        Cursor cursor = getContentResolver().query(CONTENT_URI, null,
                null,
                null, null);
        adapter.changeCursor(cursor);
    }//

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapter != null && adapter.getCursor() != null) {
            adapter.getCursor().close();
        }
    }
}
