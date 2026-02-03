package com.example.pokemon.pokeapi;

import java.util.List;
import java.util.ArrayList;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PokeApi {
    
    private final RestClient restClient;
    private final String baseUrl;
    private final int totalPokemon;

    public PokeApi(RestClient.Builder restClientBuilder) {
        totalPokemon = 50;
        baseUrl = "https://pokeapi.co/api/v2/pokemon/";
        restClient = restClientBuilder.baseUrl(baseUrl).build();
    }

    @Cacheable("pokemon")
    public List<PokeApiDto> getAllPokemon() {
        List<PokeApiDto> allPokemon = new ArrayList<>();

        for (int i = 1; i <= totalPokemon; i++) {
            allPokemon.add(getPokemon(i));
        }

        return allPokemon;
    }

    public PokeApiDto getPokemon(int number) {
        return restClient.get().uri(baseUrl + number).retrieve().body(PokeApiDto.class);
    }
}