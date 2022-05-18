package br.com.devrmartins.listacomrecyclerview;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

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
    ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shimmerFrameLayout = findViewById(R.id.placeholder);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                PokemonService.getPokemons(new IGetPokemonsCallback() {
                    @Override
                    public void onResponse(List<Pokemon> pokemons) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        initRecyclerView(pokemons);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        showToast(t.getMessage());
                    }
                });
            }
        }, 3000);
    }

    private void initRecyclerView(List<Pokemon> pokemons) {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setVisibility(View.VISIBLE);
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