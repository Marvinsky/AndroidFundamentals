package com.example.mabisrror.javafundamentalsforandroid.clase07.tarea3.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import com.example.mabisrror.javafundamentalsforandroid.clase07.tarea3.bd.ClientesSqliteHelper;
import com.example.mabisrror.javafundamentalsforandroid.clase07.tarea3.bd.DatabaseHelper;

import java.io.IOException;

public class ClientesProvider2 extends ContentProvider {
    //Definición del CONTENT_URI
    private static final String uri =
            "content://com.example.mabisrror.javafundamentalsforandroid2/clientes";

    public static final Uri CONTENT_URI = Uri.parse(uri);

    //Necesario para UriMatcher
    private static final int CLIENTES = 1;
    private static final int CLIENTES_ID = 2;
    private static final UriMatcher uriMatcher;

    //BD
    private DatabaseHelper clidb;
    private SQLiteDatabase myDatabase;
    private static final String TABLA_CLIENTES = "tbclientes";

    //Clase interna para declarar las constantes de columna
    public static final class Clientes2 implements BaseColumns {
        private Clientes2() {
        }

        //Nombres de columnas
        public static final String COL_NOMBRE = "cliente";
        public static final String COL_TELEFONO = "telefono";
        public static final String COL_EMAIL = "email";
    }

    //Inicializamos el UriMatcher
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.mabisrror.javafundamentalsforandroid2", "clientes", CLIENTES);
        uriMatcher.addURI("com.example.mabisrror.javafundamentalsforandroid2", "clientes/#", CLIENTES_ID);
    }


    public ClientesProvider2() {
    }

    @Override
    public boolean onCreate() {
        clidb = new DatabaseHelper(getContext());
        try {
            clidb.createDatabase();
        } catch (IOException e) {
            throw new Error("Database can not be created due: " + e.getMessage());
        }
        myDatabase = clidb.openDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if(uriMatcher.match(uri) == CLIENTES_ID){
            where = "_id=" + uri.getLastPathSegment();
        }

        Cursor c = myDatabase.query(TABLA_CLIENTES, projection, where,
                selectionArgs, null, null, sortOrder);

        return c;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long regId = 1;

        regId = myDatabase.insert(TABLA_CLIENTES, null, values);

        Uri newUri = ContentUris.withAppendedId(CONTENT_URI, regId);

        return newUri;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int cont;

        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if(uriMatcher.match(uri) == CLIENTES_ID){
            where = "_id=" + uri.getLastPathSegment();
        }

        cont = myDatabase.update(TABLA_CLIENTES, values, where, selectionArgs);

        return cont;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int cont;

        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if(uriMatcher.match(uri) == CLIENTES_ID){
            where = "_id=" + uri.getLastPathSegment();
        }

        cont = myDatabase.delete(TABLA_CLIENTES, where, selectionArgs);

        return cont;
    }

    @Override
    public String getType(Uri uri) {
        int match = uriMatcher.match(uri);

        switch (match)
        {
            case CLIENTES:
                return "vnd.android.cursor.dir/vnd.sgoliver.cliente";
            case CLIENTES_ID:
                return "vnd.android.cursor.item/vnd.sgoliver.cliente";
            default:
                return null;
        }
    }

}
