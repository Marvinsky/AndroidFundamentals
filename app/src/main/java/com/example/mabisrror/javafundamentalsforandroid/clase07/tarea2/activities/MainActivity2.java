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
import com.example.mabisrror.javafundamentalsforandroid.clase07.tarea2.service.MyTestService2;

public class MainActivity2 extends AppCompatActivity {

    Button btnStart, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
    }

    private void init() {
        btnStart = (Button)findViewById(R.id.btn_start_service);
        btnStop = (Button)findViewById(R.id.btn_stop_service);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getBaseContext(), MyTestService2.class));
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getBaseContext(), MyTestService2.class));
            }
        });

    }

}
