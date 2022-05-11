package br.com.devrmartins.listacomrecyclerview.models.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.devrmartins.listacomrecyclerview.models.Pokemon;

public class PokemonResponse {
    List<Pokemon> results = new ArrayList<Pokemon>();

    public List<Pokemon> getResults() {
        return results;
    }

    public void setResults(List results) {
        this.results = results;
    }
}
