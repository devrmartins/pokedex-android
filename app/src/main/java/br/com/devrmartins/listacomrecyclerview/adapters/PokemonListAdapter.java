package br.com.devrmartins.listacomrecyclerview.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.com.devrmartins.listacomrecyclerview.R;
import br.com.devrmartins.listacomrecyclerview.models.Pokemon;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.PokemonListViewHolder> {

    private List<Pokemon> pokemonList;
    private PokemonListClickListener clickListener;

    public PokemonListAdapter(List<Pokemon> pokemonList, PokemonListClickListener clickListener) {
        this.pokemonList = pokemonList;
        this.clickListener = clickListener;
    }

    public void updateData(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PokemonListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_list_row, parent, false);
        return new PokemonListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonListAdapter.PokemonListViewHolder holder, int position) {
        Pokemon pokemon = pokemonList.get(position);
        holder.pokemonName.setText(pokemon.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(pokemon);
            }
        });
        String[] split = pokemon.getUrl().substring(0, pokemon.getUrl().length() - 1).split("/");
        String pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + split[split.length - 1] + ".png";
        String imageNumber = "";
        int index = pokemonList.indexOf(pokemon) + 1;
        if (index < 10) {
            imageNumber = "00" + index;
        } else if (index < 100) {
            imageNumber = "0" + index;
        } else {
            imageNumber = String.valueOf(index);
        }
        String pokemonImageByGithub = "https://raw.githubusercontent.com/PokeMiners/pogo_assets/master/Images/Pokemon/pokemon_icon_" + imageNumber + "_00.png";
        System.out.println(imageNumber);
        Glide.with(holder.pokemonImage)
                .load(pokemonImageByGithub)
                .into(holder.pokemonImage);
    }

    @Override
    public int getItemCount() {
        return this.pokemonList.size();
    }

    static class PokemonListViewHolder extends RecyclerView.ViewHolder {
        TextView pokemonName;
        ImageView pokemonImage;

        public PokemonListViewHolder(View view) {
            super(view);

            pokemonName = view.findViewById(R.id.name);
            pokemonImage = view.findViewById(R.id.thumbImage);



        }
    }

    public interface PokemonListClickListener {
        public void onItemClick(Pokemon pokemon);
    }
}
