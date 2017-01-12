package com.example.mabisrror.javafundamentalsforandroid.clase05;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mabisrror.javafundamentalsforandroid.R;

import org.w3c.dom.Text;

public class Cronometro extends AppCompatActivity {
    Button iniciar, pausar, reiniciar;
    TextView tvChrono, tvTitle;
    Chronometer chronometer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);
        init();

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chronometer == null) {
                    chronometer = new Chronometer(getResources().getString(R.string.begin_chrono), tvTitle, tvChrono);
                    new Thread(chronometer).start();
                } else {
                    Toast.makeText(Cronometro.this, getResources().getString(R.string.current_chrono_stopped), Toast.LENGTH_SHORT).show();
                }
            }
        });

        pausar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.pause();
                if (pausar.getText().equals(getResources().getString(R.string.pausar))) {
                    pausar.setText(getResources().getString(R.string.continue_chrono));
                } else {
                    pausar.setText(getResources().getString(R.string.pausar));
                }
            }
        });

        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pausar.getText().equals(getResources().getString(R.string.continue_chrono))) {
                    pausar.setText(getResources().getString(R.string.pausar));
                }
                chronometer.restart();
            }
        });
    }

    private void init() {
        iniciar = (Button)findViewById(R.id.btn_iniciar);
        pausar = (Button)findViewById(R.id.btn_pausar);
        reiniciar = (Button)findViewById(R.id.btn_reiniciar);
        tvChrono = (TextView)findViewById(R.id.tvCronometro);
        tvTitle = (TextView)findViewById(R.id.textViewTitle);
    }

    public class Chronometer implements Runnable {
        private TextView chrono, title;
        private String titleChrono;
        private int seconds, minutes, hours;
        private Handler writeUI;
        private boolean stopped;
        private String output;
        private volatile boolean running = true;

        public void terminate() {
            //TODO destroy thread, because it won't work anymore
            running = false;
        }

        public Chronometer(String strTitle, TextView tvTitle, TextView tvChrono) {
            titleChrono = strTitle;
            chrono = tvChrono;
            title = tvTitle;
            seconds = 0;
            minutes = 0;
            hours = 0;
            writeUI = new Handler();
            stopped = false;
            output = "";
        }

        @Override
        public void run() {
            try {
                while (running) {
                    Thread.sleep(1000);
                    output = "";
                    if (!stopped) {
                        seconds++;
                        if (seconds == 60) {
                            seconds = 0;
                            minutes++;
                        }
                        if (minutes == 60) {
                            minutes = 0;
                            hours++;
                        }

                        if (hours <= 9) {
                            output += "0";
                        }
                        output += hours;
                        output += "hours";
                        output += ":";
                        if (minutes <= 9) {
                            output += "0";
                        }
                        output += minutes;
                        output += ":";
                        if (seconds <= 9) {
                            output += "0";
                        }
                        output += seconds;
                        try {
                            writeUI.post(new Runnable() {
                                @Override
                                public void run() {
                                    title.setText(titleChrono);
                                    chrono.setText(output);
                                }
                            });
                        } catch (Exception e) {
                            running = false;
                            throw new Error("Error message: " + e.getMessage());
                        }

                    }
                }
            } catch (InterruptedException e) {
                running = false;
                throw new Error("Error message: " + e.getMessage());
            }
        }

        public void restart() {
            seconds = 0;
            minutes = 0;
            hours = 0;
            stopped = false;
        }

        public void pause() {
            stopped = !stopped;
        }

    }
}
