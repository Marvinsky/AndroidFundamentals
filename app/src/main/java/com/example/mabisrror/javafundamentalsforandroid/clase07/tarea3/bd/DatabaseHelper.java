package com.example.mabisrror.javafundamentalsforandroid.clase07.tarea3.bd;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by mabisrror on 1/18/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.example.mabisrror.javafundamentalsforandroid/databases/";
    private static String DB_NAME = "dbdemo.sqlite";
    private Context myContext;
    private SQLiteDatabase myDatabase;
    private static final int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        this.myContext = context;
        this.DB_PATH = this.myContext.getDatabasePath(DB_NAME).getAbsolutePath();
    }


    public void createDatabase() throws IOException {
        boolean dbExists = checkDatabase();
        SQLiteDatabase db_read = null;
        if (!dbExists) {
            //if (true) {
            db_read = this.getReadableDatabase();
            db_read.close();
            try{
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Fail copying database");
            }
        }
    }

    private void copyDataBase() throws IOException{
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) != -1) {
            if (length > 0) {
                myOutput.write(buffer, 0, length);
            }
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public SQLiteDatabase openDatabase() throws SQLException {
        String myPath = DB_PATH;
        myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        return myDatabase;
    }

    public synchronized void close() {
        if (myDatabase != null) {
            myDatabase.close();
        }
        super.close();
    }

    public boolean checkDatabase() {
        SQLiteDatabase checkDB;
        String pathDB = DB_PATH;
        try {
            checkDB = SQLiteDatabase.openDatabase(pathDB, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            File dbFile = new File(pathDB);
            return dbFile.exists();
        }

        if (checkDB != null) {
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion)
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
