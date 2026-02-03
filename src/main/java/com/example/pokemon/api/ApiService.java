package com.example.pokemon.api;

import org.springframework.stereotype.Service;

import com.example.pokemon.pokeapi.PokeApi;

@Service
public class ApiService {
    
    private final PokeApi pokeApi;

    public ApiService(PokeApi pokeApi) {
        this.pokeApi = pokeApi;
    }

    public void getAllPokemon() {
        this.pokeApi.getAllPokemon();
    }
}
