package com.example.mabisrror.javafundamentalsforandroid.clase07.tarea2.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.mabisrror.javafundamentalsforandroid.R;

public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String strAlarm = "Alarm!";
        Toast.makeText(context, strAlarm, Toast.LENGTH_SHORT).show();
    }
}
