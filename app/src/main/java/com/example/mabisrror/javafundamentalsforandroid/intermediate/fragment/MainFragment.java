package com.example.mabisrror.javafundamentalsforandroid.intermediate.fragment;

import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;


import com.example.mabisrror.javafundamentalsforandroid.R;
import com.example.mabisrror.javafundamentalsforandroid.intermediate.activities.DetailActivity;
import com.example.mabisrror.javafundamentalsforandroid.intermediate.activities.InsertActivity;
import com.example.mabisrror.javafundamentalsforandroid.intermediate.adapter.ActivitiesAdapter;
import com.example.mabisrror.javafundamentalsforandroid.intermediate.contract.TechsContract;
import com.example.mabisrror.javafundamentalsforandroid.intermediate.listener.OnCPListener;

/**
 * Created by mabisrror on 3/2/17.
 */

public class MainFragment extends ListFragment
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private OnCPListener mListener;

    /*
    Adaptador
     */
    private ActivitiesAdapter adapter;

    public MainFragment() {
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCPListener) {
            mListener = (OnCPListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCPListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //CP implementation
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ImageButton fab = (ImageButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), InsertActivity.class));
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Iniciar adaptador
        adapter = new ActivitiesAdapter(getActivity());
        setListAdapter(adapter);
        //Iniciar loader
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        //Consiltar todos los registros
        return new CursorLoader(
                getActivity(),
                TechsContract.CONTENT_URI,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            getLoaderManager().destroyLoader(0);
            if (adapter != null) {
                adapter.changeCursor(null);
                adapter = null;
            }
        } catch (Throwable localThrowable) {

        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        getActivity().startActivity(
                new Intent(getActivity(), DetailActivity.class)
                .putExtra(TechsContract.Columnas._ID, id)
        );
    }
}
