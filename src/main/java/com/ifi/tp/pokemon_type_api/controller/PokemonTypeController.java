package com.ifi.tp.pokemon_type_api.controller;

import com.ifi.tp.pokemon_type_api.bo.PokemonType;
import com.ifi.tp.pokemon_type_api.service.PokemonTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon-types")
class PokemonTypeController {

    private PokemonTypeService pokemonTypeService;

    PokemonTypeController(PokemonTypeService service) {
        this.pokemonTypeService = service;
    }

    @GetMapping("/{id}")
    PokemonType getPokemonTypeFromId(@PathVariable int id){
        return this.pokemonTypeService.getPokemonType(id);
    }

    @GetMapping("/")
    List<PokemonType> getAllPokemonTypes() {
        return this.pokemonTypeService.getAllPokemonTypes();
    }

    @GetMapping(path = "/", params = "name")
    PokemonType getPokemonTypeFromName(@RequestParam String name) {
        return this.pokemonTypeService.getPokemonTypeByName(name);
    }

    @GetMapping(path = "/", params = "types")
    List<PokemonType> getPokemonsFromTypes(@RequestParam List<String> types) {
        return this.pokemonTypeService.getPokemonsByTypes(types);
    }
}
