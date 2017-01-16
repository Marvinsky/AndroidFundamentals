package com.example.mabisrror.javafundamentalsforandroid.clase07.act2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mabisrror.javafundamentalsforandroid.R;
import com.example.mabisrror.javafundamentalsforandroid.clase07.act3.ThreeActivity;
import com.example.mabisrror.javafundamentalsforandroid.clase07.act4.FourActivity;
import com.example.mabisrror.javafundamentalsforandroid.clase07.act5.FiveActivity;

public class TwoActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        init();

    }

    public void intentThree() {
        Intent intent = new Intent(TwoActivity.this, ThreeActivity.class);
        startActivity(intent);
    }


    public void intentFour() {
        Intent intent = new Intent(TwoActivity.this, FourActivity.class);
        startActivity(intent);
    }


    public void intentFive() {
        Intent intent = new Intent(TwoActivity.this, FiveActivity.class);
        startActivity(intent);
    }


    private void init() {
        btn1 = (Button)findViewById(R.id.btn_1);
        btn2 = (Button)findViewById(R.id.btn_2);
        btn3 = (Button)findViewById(R.id.btn_3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentThree();
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentFour();
            }
        });


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentFive();
            }
        });




    }
}
