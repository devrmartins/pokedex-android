package br.com.devrmartins.listacomrecyclerview.api;

import br.com.devrmartins.listacomrecyclerview.models.dto.PokemonResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IPokemenonApi extends IApi {
    @GET("pokemon")
    Call<PokemonResponse> getPokemons();
}
