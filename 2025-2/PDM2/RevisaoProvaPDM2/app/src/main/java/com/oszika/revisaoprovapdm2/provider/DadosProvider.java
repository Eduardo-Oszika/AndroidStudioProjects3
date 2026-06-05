package com.oszika.revisaoprovapdm2.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.oszika.revisaoprovapdm2.dao.AppDao;
import com.oszika.revisaoprovapdm2.dao.AppDatabase;
import com.oszika.revisaoprovapdm2.entity.Professor;
import com.oszika.revisaoprovapdm2.entity.Resultado;


public class DadosProvider extends ContentProvider {
    private AppDao dao;
    public static final String AUTHORITY = "com.oszika.revisaoprovapdm2.provider";
    private static final String PATH_PROFESSOR = "professor";
    private static final String PATH_RESULTADO = "resultado";
    public static final Uri PROFESSOR_URI =
            Uri.parse("content://" + AUTHORITY + "/" + PATH_PROFESSOR);

    public static final Uri RESULTADO_URI =
            Uri.parse("content://" + AUTHORITY + "/" + PATH_RESULTADO);

    private static final int PROFESSOR_TODOS = 1;
    private static final int PROFESSOR_UNICO = 2;
    private static final int RESULTADO_TODOS = 3;
    private static final int RESULTADO_UNICO = 4;

    private static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

        URI_MATCHER.addURI(AUTHORITY, PATH_PROFESSOR, PROFESSOR_TODOS);
        URI_MATCHER.addURI(AUTHORITY, PATH_PROFESSOR + "/#", PROFESSOR_UNICO);

        URI_MATCHER.addURI(AUTHORITY, PATH_RESULTADO, RESULTADO_TODOS);
        URI_MATCHER.addURI(AUTHORITY, PATH_RESULTADO + "/#", RESULTADO_UNICO);
    }

    @Override
    public boolean onCreate() {
        AppDatabase db = AppDatabase.getDatabase(getContext());
        dao = db.appDao();
        return true;
    }


    @Override
    public int delete(@NonNull Uri uri,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs) {

        int count;

        switch (URI_MATCHER.match(uri)) {

            case PROFESSOR_TODOS:
                count = dao.deletarTodosProfessores();
                break;

            case PROFESSOR_UNICO:
                count = dao.deletarProfessorPorId(ContentUris.parseId(uri));
                break;

            case RESULTADO_TODOS:
                count = dao.deletarTodosResultados();
                break;

            case RESULTADO_UNICO:
                count = dao.deletarResultadoPorId(ContentUris.parseId(uri));
                break;

            default:
                throw new IllegalArgumentException("URI inválida para delete");
        }

        if (count > 0)
            getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        switch (URI_MATCHER.match(uri)) {

            case PROFESSOR_TODOS:
                return "vnd.android.cursor.dir/vnd." + AUTHORITY + ".professor";

            case PROFESSOR_UNICO:
                return "vnd.android.cursor.item/vnd." + AUTHORITY + ".professor";

            case RESULTADO_TODOS:
                return "vnd.android.cursor.dir/vnd." + AUTHORITY + ".resultado";

            case RESULTADO_UNICO:
                return "vnd.android.cursor.item/vnd." + AUTHORITY + ".resultado";

            default:
                throw new IllegalArgumentException("URI inválida");
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        if (values == null) return null;

        switch (URI_MATCHER.match(uri)) {

            case PROFESSOR_TODOS:

                Professor professor = new Professor();
                professor.username = values.getAsString("professor_username");
                professor.senha = values.getAsString("professor_senha");

                long profId = dao.insertProfessor(professor);

                if (profId > 0) {
                    Uri uriNovo = ContentUris.withAppendedId(PROFESSOR_URI, profId);
                    getContext().getContentResolver().notifyChange(uriNovo, null);
                    return uriNovo;
                }
                break;

            case RESULTADO_TODOS:

                Resultado resultado = new Resultado();
                resultado.disciplina = values.getAsString("disciplina_nome");
                resultado.nomeAluno = values.getAsString("aluno_nome");
                resultado.notaFinal = values.getAsFloat("aluno_nota_final");
                resultado.status = values.getAsString("aluno_status");

                long resId = dao.insertResultado(resultado);

                if (resId > 0) {
                    Uri uriNovo = ContentUris.withAppendedId(RESULTADO_URI, resId);
                    getContext().getContentResolver().notifyChange(uriNovo, null);
                    return uriNovo;
                }
                break;

            default:
                throw new IllegalArgumentException("URI inválida para insert");
        }

        return null;
    }


    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri,
                        @Nullable String[] projection,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        Cursor cursor;
        switch (URI_MATCHER.match(uri)) {

            // ===== PROFESSOR =====

            case PROFESSOR_TODOS:

                if ("login".equals(selection) && selectionArgs != null) {
                    cursor = dao.loginCursor(selectionArgs[0], selectionArgs[1]);
                } else {
                    cursor = dao.selectAllProfessoresCursor();
                }
                break;

            case PROFESSOR_UNICO:
                long profId = ContentUris.parseId(uri);
                cursor = dao.selectProfessorByIdCursor(profId);
                break;


            case RESULTADO_TODOS:
                cursor = dao.selectAllResultadosCursor();
                break;

            case RESULTADO_UNICO:
                long resId = ContentUris.parseId(uri);
                cursor = dao.selectResultadoByIdCursor(resId);
                break;

            default:
                throw new IllegalArgumentException("URI desconhecida");
        }

        if (cursor != null)
            cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public int update(@NonNull Uri uri,
                      @Nullable ContentValues values,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs) {

        if (values == null) return 0;

        int count;

        switch (URI_MATCHER.match(uri)) {

            case PROFESSOR_UNICO:

                Professor professor = new Professor();
                professor.id = ContentUris.parseId(uri);
                professor.username = values.getAsString("professor_username");
                professor.senha = values.getAsString("professor_senha");

                count = dao.atualizarProfessor(professor);
                break;

            case RESULTADO_UNICO:

                Resultado resultado = new Resultado();
                resultado.id = ContentUris.parseId(uri);
                resultado.disciplina = values.getAsString("disciplina_nome");
                resultado.nomeAluno = values.getAsString("aluno_nome");
                resultado.notaFinal = values.getAsFloat("aluno_nota_final");
                resultado.status = values.getAsString("aluno_status");

                count = dao.atualizarResultado(resultado);
                break;

            default:
                throw new IllegalArgumentException("Update precisa de ID");
        }

        if (count > 0)
            getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }
}
