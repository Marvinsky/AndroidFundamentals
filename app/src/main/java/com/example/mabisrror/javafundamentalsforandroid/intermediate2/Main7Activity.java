package com.example.mabisrror.javafundamentalsforandroid.intermediate2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.mabisrror.javafundamentalsforandroid.R;
import com.example.mabisrror.javafundamentalsforandroid.intermediate2.model.Pokemon;
import com.example.mabisrror.javafundamentalsforandroid.intermediate2.retrofit.PokemonEndPoint;
import com.example.mabisrror.javafundamentalsforandroid.intermediate2.retrofit.PokemonResponse;
import com.example.mabisrror.javafundamentalsforandroid.intermediate2.retrofit.PokemonRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main7Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        PokemonEndPoint service = (new PokemonRetrofit()).getService();
        Call<PokemonResponse> call = service.getPokemons();
        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("RET", "SUCCESS");
                    PokemonResponse result = response.body();
                    Pokemon pokemon = result.getPokemon();

                    Log.d("id", pokemon.getId());
                    Log.d("name", pokemon.getName());
                    Log.d("base_experience", String.valueOf(pokemon.getBase_experience()));
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                Log.d("RET", "FAILED");
            }
        });



    }
}
