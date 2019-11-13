package com.ifi.tp.pokemon_type_api.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifi.tp.pokemon_type_api.bo.PokemonType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class PokemonTypeRepositoryImpl implements PokemonTypeRepository {

    private List<PokemonType> pokemons;

    public PokemonTypeRepositoryImpl() {
        try {
            var pokemonsStream = new ClassPathResource("pokemons.json").getInputStream();

            var objectMapper = new ObjectMapper();
            var pokemonsArray = objectMapper.readValue(pokemonsStream, PokemonType[].class);
            this.pokemons = Arrays.asList(pokemonsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PokemonType findPokemonTypeById(int id) {
        System.out.println("Loading Pokemon information for Pokemon id " + id);

        for(PokemonType pokemon : pokemons) {
            if(pokemon.getId() == id) {
                return pokemon;
            }
        }
        return null;
    }

    @Override
    public PokemonType findPokemonTypeByName(String name) {
        for(PokemonType pokemon : pokemons) {
            if(name.equalsIgnoreCase(pokemon.getName())) {
                return pokemon;
            }
        }
        return null;
    }

    @Override
    public List<PokemonType> findAllPokemonType() {
        return this.pokemons;
    }

    @Override
    public List<PokemonType> findPokemonsByTypes(List<String> types) {
        List<PokemonType> res = new ArrayList<>();

        for (PokemonType pokemon : pokemons) {
            List<String> pokemonTypes = pokemon.getTypes();
            if (pokemonTypes.containsAll(types)) {
                    res.add(pokemon);
            }
        }
        return res;
    }
}
