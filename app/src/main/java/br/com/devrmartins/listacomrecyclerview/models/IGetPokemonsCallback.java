package br.com.devrmartins.listacomrecyclerview.models;

import java.util.List;

public interface IGetPokemonsCallback {
    void onResponse(List<Pokemon> pokemons);
    void onFailure(Throwable t);
}
