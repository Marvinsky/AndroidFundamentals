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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mabisrror.javafundamentalsforandroid.R;
import com.example.mabisrror.javafundamentalsforandroid.clase07.tarea3.contentprovider.ClientesProvider;
import com.example.mabisrror.javafundamentalsforandroid.clase07.tarea3.contentprovider.ClientesProvider.Clientes;

import org.w3c.dom.Text;

public class MainActivity4 extends AppCompatActivity {

    Button btnConsultar, btnInsertar, btnEliminar, btnLlamadas;
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
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
                String[] projection = new String[]{
                        Clientes._ID,
                        Clientes.COL_NOMBRE,
                        Clientes.COL_TELEFONO,
                        Clientes.COL_EMAIL
                };

                Uri clientesUri = ClientesProvider.CONTENT_URI;

                ContentResolver cr = getContentResolver();

                //Hacemos una consulta
                Cursor cur = cr.query(clientesUri,
                        projection, //Columnas a devolver
                        null, //Condicion de la query
                        null, //Argumentos de variables de la query
                        null); //Orden de los resultados

                if (cur.moveToFirst()) {
                    String nombre, telefono, email;
                    int colNombre = cur.getColumnIndex(Clientes.COL_NOMBRE);
                    int colTelefono = cur.getColumnIndex(Clientes.COL_TELEFONO);
                    int colEmail = cur.getColumnIndex(Clientes.COL_EMAIL);

                    tvResultado.setText("");

                    do {
                        nombre = cur.getString(colNombre);
                        telefono = cur.getString(colTelefono);
                        email = cur.getString(colEmail);

                        tvResultado.append(nombre + " - " + telefono + " - " + email + "\n");
                    } while (cur.moveToNext());
                }
            }
        });

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(Clientes.COL_NOMBRE, "ClienteN");
                values.put(Clientes.COL_TELEFONO, "937800726");
                values.put(Clientes.COL_EMAIL, "marvin@abisrror.com");

                ContentResolver cr = getContentResolver();
                cr.insert(ClientesProvider.CONTENT_URI, values);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentResolver cr = getContentResolver();

                cr.delete(ClientesProvider.CONTENT_URI,
                        Clientes.COL_NOMBRE + " = 'ClienteN'", null);
            }
        });

        btnLlamadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] projection = new String[]{
                        Calls.TYPE,
                        Calls.NUMBER
                };

                Uri llamadaUri = Calls.CONTENT_URI;
                ContentResolver cr = getContentResolver();


                if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                    tvResultado.setText("");
                    tvResultado.setText(getResources().getString(R.string.txt_check_permissions));
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.txt_give_permission_to_app), Toast.LENGTH_SHORT).show();
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                Cursor cursor = cr.query(llamadaUri, projection, null, null, null);

                if (cursor.moveToFirst()) {
                    int tipo;
                    String tipoLlamada = "";
                    String telefono;

                    int colTipo = cursor.getColumnIndex(Calls.TYPE);
                    int colTelefono = cursor.getColumnIndex(Calls.NUMBER);

                    tvResultado.setText("");

                    do {
                        tipo = cursor.getInt(colTipo);
                        telefono = cursor.getString(colTelefono);
                        if (tipo == Calls.INCOMING_TYPE) {
                            tipoLlamada = "ENTRADA";
                        } else if (tipo == Calls.OUTGOING_TYPE) {
                            tipoLlamada = "SALIDA";
                        } else if (tipo == Calls.MISSED_TYPE){
                            tipoLlamada = "PERDIDA";
                        }

                        tvResultado.append(tipoLlamada + " - " + telefono + "\n");
                    } while (cursor.moveToNext());
                }
            }
        });
    }
}
