package br.com.devrmartins.listacomrecyclerview.api.services;

import java.util.List;

import br.com.devrmartins.listacomrecyclerview.api.IPokemenonApi;
import br.com.devrmartins.listacomrecyclerview.api.PokemonApi;
import br.com.devrmartins.listacomrecyclerview.models.IGetPokemonsCallback;
import br.com.devrmartins.listacomrecyclerview.models.Pokemon;
import br.com.devrmartins.listacomrecyclerview.models.dto.PokemonResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonService {
    public static void getPokemons(IGetPokemonsCallback callback) {
        IPokemenonApi api = (IPokemenonApi) PokemonApi.getInstance();
        Call<PokemonResponse> call = api.getPokemons();

        call.enqueue(new Callback<PokemonResponse>() {

            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if(!response.isSuccessful()) {
                    callback.onFailure(new Throwable("Não foi possível carregar a lista de pokemons. Code: " + response.code()));
                    return;
                }

                PokemonResponse pokemonResponse = response.body();
                List<Pokemon> pokemons = pokemonResponse.getResults();

                callback.onResponse(pokemons);

            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
