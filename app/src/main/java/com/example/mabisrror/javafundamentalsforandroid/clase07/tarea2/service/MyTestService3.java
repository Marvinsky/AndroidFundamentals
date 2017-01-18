package com.example.mabisrror.javafundamentalsforandroid.clase07.tarea2.service;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by mabisrror on 1/17/17.
 */

public class MyTestService3 extends IntentService {

    public static final String ACTION_PROGRESO = "com.example.mabisrror.javafundamentalsforandroid.action.PROGRESO";
    public static final String ACTION_FIN = "com.example.mabisrror.javafundamentalsforandroid.action.FIN";

    public MyTestService3() {
        super("MyTestService3");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int iter = intent.getIntExtra("iteraciones", 0);
        for (int i = 0; i < iter; i++) {
            tareaLarga();
            //Comunicamos el proceso
            Intent intent1 = new Intent();
            intent1.setAction(ACTION_PROGRESO);
            intent1.putExtra("progreso", i * 10);
            sendBroadcast(intent1);
        }

        Intent intent2 = new Intent();
        intent2.setAction(ACTION_FIN);
        sendBroadcast(intent2);
    }

    public void tareaLarga() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
