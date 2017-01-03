package com.example.mabisrror.javafundamentalsforandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LineasActivity extends Activity {

    EditText nLineas;
    TextView resultado;
    Button btnAgregarlineas;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lineas);
        init();

        btnAgregarlineas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                crearLineas();
            }
        });

    }

    public void init() {
        nLineas = (EditText)findViewById(R.id.numeroLineas);
        resultado = (TextView)findViewById(R.id.resultado);
        btnAgregarlineas = (Button)findViewById(R.id.agregarLineas);
        linearLayout = (LinearLayout)findViewById(R.id.linearLayoutContainer);
    }

    public void crearLineas() {
        int lineas = Integer.parseInt(nLineas.getText().toString());
        validarLineas(lineas);
        for (int i = 0; i < lineas; i++) {
            TextView textView = new TextView(this);
            textView.setText(getResources().getString(R.string.linea_text) + " " + String.valueOf(i + 1));
            linearLayout.addView(textView);
        }
    }

    private void validarLineas(int lineas) {
        if (lineas < 1) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.validar_lineas), Toast.LENGTH_SHORT).show();
        }
    }


}
