package com.example.mabisrror.javafundamentalsforandroid.intermediate.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mabisrror.javafundamentalsforandroid.intermediate.contract.TechsContract;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by mabisrror on 3/1/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context,
                          String name,
                          SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        createTable(database);
        loadDummyData(database);
    }

    private void createTable(SQLiteDatabase database) {
        String cmd = "CREATE TABLE " + TechsContract.ACTIVIDAD + " (" +
                TechsContract.Columnas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TechsContract.Columnas.CATEGORIA + " TEXT, " +
                TechsContract.Columnas.PRIORIDAD + " TEXT, " +
                TechsContract.Columnas.ESTADO + " TEXT, " +
                TechsContract.Columnas.TECNICO + " TEXT, " +
                TechsContract.Columnas.DESCRIPCION + " TEXT) ";
        database.execSQL(cmd);
    }


    private void loadDummyData(SQLiteDatabase database) {
        ContentValues values = new ContentValues();
        values.put(TechsContract.Columnas.CATEGORIA, "Factibilidad");
        values.put(TechsContract.Columnas.PRIORIDAD, "Media");
        values.put(TechsContract.Columnas.ESTADO, "Abierta");
        values.put(TechsContract.Columnas.TECNICO, "Juan Pedrozo");
        values.put(TechsContract.Columnas.DESCRIPCION, "LLevar router MX230");
        database.insert(TechsContract.ACTIVIDAD, null, values);

        values = new ContentValues();
        values.put(TechsContract.Columnas.CATEGORIA, "Reparación");
        values.put(TechsContract.Columnas.PRIORIDAD, "Alta");
        values.put(TechsContract.Columnas.ESTADO, "En Curso");
        values.put(TechsContract.Columnas.TECNICO, "Mirta Gomez");
        values.put(TechsContract.Columnas.DESCRIPCION, "Internet intermitente");
        database.insert(TechsContract.ACTIVIDAD, null, values);

        values = new ContentValues();
        values.put(TechsContract.Columnas.CATEGORIA, "Traslado");
        values.put(TechsContract.Columnas.PRIORIDAD, "Baja");
        values.put(TechsContract.Columnas.ESTADO, "Cerrada");
        values.put(TechsContract.Columnas.TECNICO, "Carlos Gutierrez");
        values.put(TechsContract.Columnas.DESCRIPCION, "Nueva dirección: Cra 4 #2C-90");
        database.insert(TechsContract.ACTIVIDAD, null, values);

        values = new ContentValues();
        values.put(TechsContract.Columnas.CATEGORIA, "Migración");
        values.put(TechsContract.Columnas.PRIORIDAD, "Baja");
        values.put(TechsContract.Columnas.ESTADO, "Abierta");
        values.put(TechsContract.Columnas.TECNICO, "Gloria Quiñonez");
        values.put(TechsContract.Columnas.DESCRIPCION, "Sustitución cable soporte ipV6");
        database.insert(TechsContract.ACTIVIDAD, null, values);

        values = new ContentValues();
        values.put(TechsContract.Columnas.CATEGORIA, "Mantenimiento");
        values.put(TechsContract.Columnas.PRIORIDAD, "Media");
        values.put(TechsContract.Columnas.ESTADO, "En Curso");
        values.put(TechsContract.Columnas.TECNICO, "Julian Arreondo");
        values.put(TechsContract.Columnas.DESCRIPCION, "LLevar Lista de checkeo rendimiento");
        database.insert(TechsContract.ACTIVIDAD, null, values);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
