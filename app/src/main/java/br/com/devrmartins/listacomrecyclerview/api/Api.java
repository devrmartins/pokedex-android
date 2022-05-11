package br.com.devrmartins.listacomrecyclerview.api;

import br.com.devrmartins.listacomrecyclerview.utils.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class Api {
    protected static IApi instance;
    protected static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    static IApi getInstance() {
        return null;
    }
}
