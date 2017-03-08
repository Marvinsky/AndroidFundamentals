package com.example.mabisrror.javafundamentalsforandroid.intermediate2.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by marvinabisrror on 8/03/17.
 */

public interface PokemonEndPoint {

    @GET("pokemon/1")
    Call<PokemonResponse> getPokemons();

}
