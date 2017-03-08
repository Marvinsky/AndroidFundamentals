package com.example.mabisrror.javafundamentalsforandroid.intermediate2.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marvinabisrror on 8/03/17.
 */

public class PokemonRetrofit {
    private Retrofit retrofit;

    public PokemonRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public PokemonEndPoint getService() {
        return retrofit.create(PokemonEndPoint.class);
    }

}
