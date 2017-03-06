package com.example.mabisrror.javafundamentalsforandroid.intermediate.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mabisrror.javafundamentalsforandroid.R;
import com.example.mabisrror.javafundamentalsforandroid.intermediate.fragment.MainFragment;
import com.example.mabisrror.javafundamentalsforandroid.intermediate.listener.OnCPListener;

public class MainActivity6 extends AppCompatActivity implements OnCPListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        if (savedInstanceState == null) {
            MainFragment mainFragment = new MainFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, mainFragment, MainFragment.class.getSimpleName())
                    .commit();
        }


    }
}
