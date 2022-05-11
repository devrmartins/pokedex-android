package br.com.devrmartins.listacomrecyclerview;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.List;

import br.com.devrmartins.listacomrecyclerview.adapters.PokemonListAdapter;
import br.com.devrmartins.listacomrecyclerview.api.Api;
import br.com.devrmartins.listacomrecyclerview.api.IPokemenonApi;
import br.com.devrmartins.listacomrecyclerview.api.PokemonApi;
import br.com.devrmartins.listacomrecyclerview.api.services.PokemonService;
import br.com.devrmartins.listacomrecyclerview.models.IGetPokemonsCallback;
import br.com.devrmartins.listacomrecyclerview.models.Pokemon;
import br.com.devrmartins.listacomrecyclerview.models.dto.PokemonResponse;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity implements PokemonListAdapter.PokemonListClickListener {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PokemonService.getPokemons(new IGetPokemonsCallback() {
            @Override
            public void onResponse(List<Pokemon> pokemons) {
                initRecyclerView(pokemons);
            }

            @Override
            public void onFailure(Throwable t) {
                showToast(t.getMessage());
            }
        });
    }

    private void initRecyclerView(List<Pokemon> pokemons) {
        this.recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        PokemonListAdapter adapter = new PokemonListAdapter(pokemons, this);
        recyclerView.setAdapter(adapter);
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(Pokemon pokemon) {
        showToast(pokemon.getUrl());
    }
}