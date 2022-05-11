package br.com.devrmartins.listacomrecyclerview.api;

import retrofit2.Retrofit;

public class PokemonApi extends Api {
    public static IApi getInstance() {
        if (instance == null) {
            Retrofit retrofit = getRetrofit();
            instance = retrofit.create(IPokemenonApi.class);
        }

        return instance;
    }
}
