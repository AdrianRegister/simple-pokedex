package com.example.pokemon.api;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pokemon.pokeapi.PokeApi;
import com.example.pokemon.pokeapi.PokeApiDto;

@Service
public class ApiService {
    
    private final PokeApi pokeApi;

    public ApiService(PokeApi pokeApi) {
        this.pokeApi = pokeApi;
    }

    public List<PokeApiDto> getAll() {
        return this.pokeApi.getAllPokemon();
    }
}
