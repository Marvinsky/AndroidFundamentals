package com.example.mabisrror.javafundamentalsforandroid.clase07.tarea3.activities;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog.Calls;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mabisrror.javafundamentalsforandroid.R;
import com.example.mabisrror.javafundamentalsforandroid.clase07.tarea3.contentprovider.ClientesProvider;
import com.example.mabisrror.javafundamentalsforandroid.clase07.tarea3.contentprovider.ClientesProvider2;
import com.example.mabisrror.javafundamentalsforandroid.clase07.tarea3.contentprovider.ClientesProvider2.Clientes2;

public class MainActivity5 extends AppCompatActivity {

    Button btnConsultar, btnInsertar, btnEliminar, btnLlamadas;
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        init();
    }

    private void init() {
        btnConsultar = (Button) findViewById(R.id.BtnConsultar);
        btnInsertar = (Button) findViewById(R.id.BtnInsertar);
        btnEliminar = (Button) findViewById(R.id.BtnEliminar);
        btnLlamadas = (Button) findViewById(R.id.BtnLlamadas);
        tvResultado = (TextView) findViewById(R.id.TxtResultados);


        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] projections = new String[]{
                        Clientes2._ID,
                        Clientes2.COL_NOMBRE,
                        Clientes2.COL_TELEFONO,
                        Clientes2.COL_EMAIL
                };

                Uri clientes2Uri = ClientesProvider2.CONTENT_URI;
                ContentResolver cr = getContentResolver();
                Cursor cursor = cr.query(clientes2Uri,
                        projections, //columnas a devolver
                        null, // condicion de la query
                        null, // argumentos de query
                        null); // orden

                if (cursor.moveToFirst()) {
                    String nombre, telefono, email;
                    int colNombre = cursor.getColumnIndex(Clientes2.COL_NOMBRE);
                    int colTelefono = cursor.getColumnIndex(Clientes2.COL_TELEFONO);
                    int colEmail = cursor.getColumnIndex(Clientes2.COL_EMAIL);
                    tvResultado.setText("");

                    do {
                        nombre = cursor.getString(colNombre);
                        telefono = cursor.getString(colTelefono);
                        email = cursor.getString(colEmail);
                        tvResultado.append(nombre + " - " + telefono + " - " + email + "\n");
                    } while (cursor.moveToNext());
                }
            }
        });

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(ClientesProvider2.Clientes2.COL_NOMBRE, "ClienteN");
                values.put(ClientesProvider2.Clientes2.COL_TELEFONO, "937800726");
                values.put(ClientesProvider2.Clientes2.COL_EMAIL, "marvin@abisrror.com");

                ContentResolver cr = getContentResolver();
                cr.insert(ClientesProvider2.CONTENT_URI, values);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentResolver cr = getContentResolver();

                cr.delete(ClientesProvider2.CONTENT_URI,
                        ClientesProvider2.Clientes2.COL_NOMBRE + " = 'ClienteN'", null);
            }
        });

        btnLlamadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] projection = new String[]{
                        Calls.TYPE,
                        Calls.NUMBER
                };

                Uri llamadasUri = Calls.CONTENT_URI;
                ContentResolver cr = getContentResolver();

                if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    tvResultado.setText("");
                    tvResultado.setText(getResources().getString(R.string.txt_check_permissions));
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.txt_give_permission_to_app), Toast.LENGTH_SHORT).show();
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                Cursor cur = cr.query(llamadasUri,
                        projection,
                        null,
                        null,
                        null);

                if (cur.moveToFirst()) {
                    int tipo;
                    String tipoLlamada = "";
                    String telefono;

                    int colTipo = cur.getColumnIndex(Calls.TYPE);
                    int colNumber = cur.getColumnIndex(Calls.NUMBER);

                    tvResultado.setText("");

                    do {
                        tipo = cur.getInt(colTipo);
                        telefono = cur.getString(colNumber);

                        if (tipo == Calls.INCOMING_TYPE) {
                            tipoLlamada = "ENTRADA";
                        } else if (tipo == Calls.OUTGOING_TYPE) {
                            tipoLlamada = "SALIDA";
                        } else if (tipo == Calls.MISSED_TYPE) {
                            tipoLlamada = "PERDIDA";
                        }

                        tvResultado.append(tipoLlamada + " - " + telefono + "\n");
                    } while (cur.moveToNext());
                }
            }
        });
    }
}
