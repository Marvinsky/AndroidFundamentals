package com.example.mabisrror.javafundamentalsforandroid.clase07.act1;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mabisrror.javafundamentalsforandroid.R;
import com.example.mabisrror.javafundamentalsforandroid.clase07.act2.TwoActivity;

public class OneActivity extends AppCompatActivity {

    ImageView bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        init();

    }

    public void goToTheBActivity() {
        Intent intent = new Intent(OneActivity.this, TwoActivity.class);
        startActivity(intent);
    }

    private void init() {
        bt1 = (ImageView)findViewById(R.id.btn1);
        bt2 = (ImageView)findViewById(R.id.btn2);
        bt3 = (ImageView)findViewById(R.id.btn3);
        bt4 = (ImageView)findViewById(R.id.btn4);
        bt5 = (ImageView)findViewById(R.id.btn5);
        bt6 = (ImageView)findViewById(R.id.btn6);
        bt7 = (ImageView)findViewById(R.id.btn7);
        bt8 = (ImageView)findViewById(R.id.btn8);
        bt9 = (ImageView)findViewById(R.id.btn9);
        bt10 = (ImageView)findViewById(R.id.btn10);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTheBActivity();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTheBActivity();
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTheBActivity();
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTheBActivity();
            }
        });

        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTheBActivity();
            }
        });

        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTheBActivity();
            }
        });

        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTheBActivity();
            }
        });

        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTheBActivity();
            }
        });

        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTheBActivity();
            }
        });

        bt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTheBActivity();
            }
        });

    }
}
