package com.example.mabisrror.javafundamentalsforandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText numero = (EditText)findViewById(R.id.numero);
        final TextView mes = (TextView)findViewById(R.id.mes);
        Button transformar = (Button)findViewById(R.id.button);

        transformar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strMes = getStringMes(Integer.parseInt(numero.getText().toString()));
                mes.setText(strMes);
            }
        });
    }

    private String getStringMes(int numero) {
        String str = "";

        switch (numero) {
            case 1:
                str = "Enero";
                break;
            case 2:
                str = "Febrero";
                break;
            case 3:
                str = "Marzo";
                break;
            case 4:
                str = "Abril";
                break;
            case 5:
                str = "Mayo";
                break;
            case 6:
                str = "Junio";
                break;
            case 7:
                str = "Julio";
                break;
            case 8:
                str = "Agosto";
                break;
            case 9:
                str = "Setiembre";
                break;
            case 10:
                str = "Octubre";
                break;
            case 11:
                str = "Noviembre";
                break;
            case 12:
                str = "Diciembre";
                break;
        }

        return str;

    }
}
