package com.example.mabisrror.javafundamentalsforandroid.intermediate.fragment;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mabisrror.javafundamentalsforandroid.R;
import com.example.mabisrror.javafundamentalsforandroid.intermediate.activities.UpdateActivity;
import com.example.mabisrror.javafundamentalsforandroid.intermediate.contract.TechsContract;
import com.example.mabisrror.javafundamentalsforandroid.intermediate.listener.OnCPListener;


public class DetailFragment extends Fragment {

    /**
     * Textos del layout
     */
    private TextView descripcion, categoria, entidad, prioridad, estado;

    /**
     * Identificador de la actividad
     */
    private long id;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        // Obtención de views
        descripcion = (TextView) view.findViewById(R.id.descripcion_text);
        categoria = (TextView) view.findViewById(R.id.categoria_text);
        entidad = (TextView) view.findViewById(R.id.tecnico_text);
        prioridad = (TextView) view.findViewById(R.id.prioridad_text);
        estado = (TextView) view.findViewById(R.id.estado_text);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        id = getActivity().getIntent().getLongExtra(TechsContract.Columnas._ID, -1);
        updateView(id);  // Actualzar la vista con los datos de la actividad
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_edit:
                beginUpdate(); // Actualizar
                return true;
            case R.id.action_eliminar:
                deleteData(); // Eliminar
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detail, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * Elimina la actividad actual
     */
    private void deleteData() {
        Uri uri = ContentUris.withAppendedId(TechsContract.CONTENT_URI, id);
        getActivity().getContentResolver().delete(
                uri,
                null,
                null
        );
    }

    /**
     * Envía todos los datos de la actividad hacia el formulario
     * de actualización
     */
    private void beginUpdate() {
        getActivity()
                .startActivity(
                        new Intent(getActivity(), UpdateActivity.class)
                                .putExtra(TechsContract.Columnas._ID, id)
                                .putExtra(TechsContract.Columnas.DESCRIPCION, descripcion.getText())
                                .putExtra(TechsContract.Columnas.CATEGORIA, categoria.getText())
                                .putExtra(TechsContract.Columnas.TECNICO, entidad.getText())
                                .putExtra(TechsContract.Columnas.PRIORIDAD, prioridad.getText())
                                .putExtra(TechsContract.Columnas.ESTADO, estado.getText())
                );
    }


    /**
     * Actualiza los textos del layout
     *
     * @param id Identificador de la actividad
     */
    private void updateView(long id) {
        if (id == -1) {
            descripcion.setText("");
            categoria.setText("");
            entidad.setText("");
            prioridad.setText("");
            estado.setText("");

            return;
        }

        Uri uri = ContentUris.withAppendedId(TechsContract.CONTENT_URI, id);
        Cursor c = getActivity().getContentResolver().query(
                uri,
                null, null, null, null);

        if (!c.moveToFirst())
            return;

        String descripcion_text = c.getString(c.getColumnIndex(TechsContract.Columnas.DESCRIPCION));
        String categoria_text = c.getString(c.getColumnIndex(TechsContract.Columnas.CATEGORIA));
        String entidad_text = c.getString(c.getColumnIndex(TechsContract.Columnas.TECNICO));
        String prioridad_text = c.getString(c.getColumnIndex(TechsContract.Columnas.PRIORIDAD));
        String estado_text = c.getString(c.getColumnIndex(TechsContract.Columnas.ESTADO));

        descripcion.setText(descripcion_text);
        categoria.setText(categoria_text);
        entidad.setText(entidad_text);
        prioridad.setText(prioridad_text);
        estado.setText(estado_text);

        c.close(); // Liberar memoria del cursor
    }
}