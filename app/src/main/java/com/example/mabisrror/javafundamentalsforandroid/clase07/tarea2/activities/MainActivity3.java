package com.example.mabisrror.javafundamentalsforandroid.clase07.tarea2.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mabisrror.javafundamentalsforandroid.R;
import com.example.mabisrror.javafundamentalsforandroid.clase07.tarea2.service.MyTestService2;
import com.example.mabisrror.javafundamentalsforandroid.clase07.tarea2.service.MyTestService3;

public class MainActivity3 extends Activity {

    Button btnejecutar;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        init();

        IntentFilter filter = new IntentFilter();
        filter.addAction(MyTestService3.ACTION_PROGRESO);
        filter.addAction(MyTestService3.ACTION_FIN);
        ProgressReceiver progressReceiver = new ProgressReceiver();
        registerReceiver(progressReceiver, filter);
    }

    private void init() {
        btnejecutar = (Button)findViewById(R.id.btn_ejecutar);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);


        btnejecutar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MyTestService3.class);
                intent.putExtra("iteraciones", 10);
                startService(intent);
            }
        });
    }

    public class ProgressReceiver extends BroadcastReceiver {
        public ProgressReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(MyTestService3.ACTION_PROGRESO)) {
                int prog = intent.getIntExtra("progreso", 0);
                progressBar.setProgress(prog);
            } else {
                Toast.makeText(MainActivity3.this, getResources().getString(R.string.txt_ended_task), Toast.LENGTH_LONG).show();
            }
        }
    }

}
