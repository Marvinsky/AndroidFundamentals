package com.example.mabisrror.javafundamentalsforandroid.clase07.tarea2.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.example.mabisrror.javafundamentalsforandroid.R;

public class MyTestService2 extends Service {
    public MyTestService2() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, getResources().getString(R.string.txt_service_started), Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, getResources().getString(R.string.txt_service_destroyed), Toast.LENGTH_SHORT).show();
    }
}
