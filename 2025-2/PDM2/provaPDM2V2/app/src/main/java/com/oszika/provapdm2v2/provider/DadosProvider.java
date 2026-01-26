package com.oszika.provapdm2v2.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Room;

import com.oszika.provapdm2v2.dao.AppDao;
import com.oszika.provapdm2v2.dao.AppDatabase;
import com.oszika.provapdm2v2.ui.entity.Personagem;

public class DadosProvider extends ContentProvider {
    private AppDatabase database;
    private AppDao dao;
    public static final String AUTHORITY = "com.oszika.provapdm2v2.provider";
    public static final String PATH = "personagens";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);

    private static final int CODIGO_URI_TODOS = 1;
    private static final int CODIGO_URI_UNICO = 2;

    private static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(AUTHORITY, PATH, CODIGO_URI_TODOS);
        URI_MATCHER.addURI(AUTHORITY, PATH + "/#", CODIGO_URI_UNICO);
    }

    @Override
    public boolean onCreate() {
            AppDatabase db = Room.databaseBuilder(getContext(),
                    AppDatabase.class, "escola.db").allowMainThreadQueries().build();
            dao = db.appDao();
        return true;
    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        int count = 0;
        switch (URI_MATCHER.match(uri)) {
            case CODIGO_URI_TODOS:
                count = dao.deletarTodosPersonagems();
                break;
            case CODIGO_URI_UNICO:
                Integer id = (int) ContentUris.parseId(uri);
                count = dao.deletarPersonagemPorId(id);
                break;
            default:
                throw new IllegalArgumentException("URI inválida para exclusão: " + uri);
        }
        if (count > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case CODIGO_URI_TODOS:
                return "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + PATH;
            case CODIGO_URI_UNICO:
                return "vnd.android.cursor.item/vnd." + AUTHORITY + "." + PATH;
            default:
                throw new IllegalArgumentException("URI inválida: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (URI_MATCHER.match(uri) != CODIGO_URI_TODOS) {
            throw new IllegalArgumentException("URI inválida para insert: " + uri);
        }
        if (values == null) return null;

        Personagem p = new Personagem();

        if (values.containsKey("personagem_nome")) {
            p.name = values.getAsString("personagem_nome");
        }
        if (values.containsKey("personagem_imageUrl")) {
            p.imageUrl = values.getAsString("personagem_imageUrl");
        }

        long id = dao.insertPersonagem(p);

        if (id > 0) {
            Uri uriNovo = ContentUris.withAppendedId(CONTENT_URI, id);
            getContext().getContentResolver().notifyChange(uriNovo, null);
            return uriNovo;
        }
        return null;
    }


    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        Cursor cursor;
        switch (URI_MATCHER.match(uri)) {
            case CODIGO_URI_TODOS:
                cursor = dao.selectAll();
                break;
            case CODIGO_URI_UNICO:
                long id = ContentUris.parseId(uri);
                cursor = dao.selectById(id);
                break;
            default:
                throw new IllegalArgumentException("URI desconhecida: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String s, @Nullable String[] strings) {
        if (URI_MATCHER.match(uri) != CODIGO_URI_UNICO) {
            throw new IllegalArgumentException("URI inválida para update. Use ID.");
        }
        if (values == null) return 0;

        Personagem p = new Personagem();
        p.id = (int) ContentUris.parseId(uri);

        if (values.containsKey("personagem_nome")) {
            p.name = values.getAsString("personagem_nome");
        }
        if (values.containsKey("personagem_imageUrl")) {
            p.imageUrl = values.getAsString("personagem_imageUrl");
        }

        int count = dao.atualizarPersonagem(p);

        if (count > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }
}
