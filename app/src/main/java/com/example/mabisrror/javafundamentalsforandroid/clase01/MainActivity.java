package com.example.mabisrror.javafundamentalsforandroid.clase01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mabisrror.javafundamentalsforandroid.R;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText numero, primero, segundo;
    TextView mes, resultado;
    Button transformar, suma, resta, multiplicacion, division;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero = (EditText)findViewById(R.id.numero);
        mes = (TextView)findViewById(R.id.mes);
        transformar = (Button)findViewById(R.id.button);

        primero = (EditText)findViewById(R.id.primero);
        segundo = (EditText)findViewById(R.id.segundo);
        suma = (Button)findViewById(R.id.operacionSuma);
        resta = (Button)findViewById(R.id.operacionResta);
        multiplicacion = (Button)findViewById(R.id.operacionMultiplicacion);
        division = (Button)findViewById(R.id.operacionDivision);
        resultado = (TextView)findViewById(R.id.resultado);

        transformar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strMes = getStringMes(Integer.parseInt(numero.getText().toString()));
                mes.setText(strMes);
            }
        });

        suma.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(primero.getText().toString());
                int b = Integer.parseInt(segundo.getText().toString());
                int r = a + b;
                String rstr = String.valueOf(r);
                resultado.setText(rstr);
            }
        });


        resta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(primero.getText().toString());
                int b = Integer.parseInt(segundo.getText().toString());
                int r = a - b;
                String rstr = String.valueOf(r);
                resultado.setText(rstr);
            }
        });

        multiplicacion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(primero.getText().toString());
                int b = Integer.parseInt(segundo.getText().toString());
                int r = a * b;
                String rstr = String.valueOf(r);
                resultado.setText(rstr);
            }
        });

        division.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(primero.getText().toString());
                int b = Integer.parseInt(segundo.getText().toString());
                int r = 0;
                String rstr = "";
                if (b != 0){
                    r = a / b;
                    rstr = String.valueOf(r);
                } else {
                    rstr = "no se puede divivir por cero";
                }
                resultado.setText(rstr);
            }
        });
    }

    private String getStringMes(int numero) {
        String str = "";

        switch (numero) {
            case 1:
                str = getResources().getString(R.string.enero);
                break;
            case 2:
                str = getResources().getString(R.string.febrero);;
                break;
            case 3:
                str = getResources().getString(R.string.marzo);;
                break;
            case 4:
                str = getResources().getString(R.string.abril);;
                break;
            case 5:
                str = getResources().getString(R.string.mayo);;
                break;
            case 6:
                str = getResources().getString(R.string.junio);;
                break;
            case 7:
                str = getResources().getString(R.string.julio);;
                break;
            case 8:
                str = getResources().getString(R.string.agosto);;
                break;
            case 9:
                str = getResources().getString(R.string.setiembre);
                break;
            case 10:
                str = getResources().getString(R.string.octubre);
                break;
            case 11:
                str = getResources().getString(R.string.noviembre);
                break;
            case 12:
                str = getResources().getString(R.string.diciembre);;
                break;
            default:
                str = getResources().getString(R.string.validacion_numero_ingresado);
                break;
        }
        return str;
    }
}
