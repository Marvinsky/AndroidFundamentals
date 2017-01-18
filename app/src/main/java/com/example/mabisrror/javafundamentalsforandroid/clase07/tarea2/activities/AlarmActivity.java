package com.example.mabisrror.javafundamentalsforandroid.clase07.tarea2.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mabisrror.javafundamentalsforandroid.R;

public class AlarmActivity extends AppCompatActivity {

    Button btnStartAlarm;
    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        init();
    }

    private void init() {
        btnStartAlarm = (Button)findViewById(R.id.btn_start_alarm);
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        btnStartAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.brounie.alarmas.action.ALARM_RECEIVER");

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        getApplicationContext(),
                        0,
                        intent,
                        0
                );
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, pendingIntent);
            }
        });
    }
}
