package com.example.mabisrror.javafundamentalsforandroid.intermediate.contract;

import android.content.UriMatcher;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by mabisrror on 3/2/17.
 */
/*
Contract class entre el provider y las aplicaciones
 */

public class TechsContract {

    /*
    Authority
     */
    public final static String AUTHORITY = "com.example.mabisrror.javafundamentalsforandroid.intermediate.TechsProvider";


    public final static String ACTIVIDAD = "actividad";
    /*
    URI de contenido principal
     */
    public final static Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + ACTIVIDAD);


    /*
    Codigo para URIs de multiples registros
     */
    public static final int ALLROWS = 1;

    /*
    Codigo para URIs de un solo registro
     */
    public static final int SINGLE_ROW = 2;

    /*
    Comparador de URIs de contenido
     */
    public static final UriMatcher uriMatcher;

    /*
    Asignacion de URIs
     */
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, ACTIVIDAD, ALLROWS);
        uriMatcher.addURI(AUTHORITY, ACTIVIDAD + "/#", SINGLE_ROW);
    }

    /*
    Tipo MIME que retorna la consulta de una sola fila
     */
    public final static String SINGLE_MINE =
            "vnd.android.cursor.item/vnd." + AUTHORITY + ACTIVIDAD;
    /*
    Tipo MEME que retorna la consulta de {@link CONTENT_URI}
     */
    public final static String MULTIPLE_MIME =
            "vnd.android.cursor.dir/vnd" + AUTHORITY + ACTIVIDAD;


    /*
    Estructura de la tabla
     */
    public static class Columnas implements BaseColumns {
        public Columnas() {
        }

        /*
        Categoria de la actividad
         */
        public final static String CATEGORIA = "categoria";
        /*
        Descripcion de la actividad
         */
        public final static String DESCRIPCION = "descripcion";
        /*
        Tecnico asignado a la actividad
         */
        public final static String TECNICO = "tecnico";
        /*
        Estado en que se encuentra la actividad
         */
        public final static String ESTADO = "estado";
        /*
        Prioridad de realizacion de la actividad
         */
        public final static String PRIORIDAD = "prioridad";

    }
}
