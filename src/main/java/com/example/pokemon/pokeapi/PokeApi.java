package com.example.pokemon.pokeapi;

import java.util.List;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import tools.jackson.databind.ObjectMapper;

@Service
public class PokeApi {
    
    private final RestClient restClient;
    private final String baseUrl;
    private final int totalPokemon;

    public PokeApi(RestClient.Builder restClientBuilder) {
        totalPokemon = 1025;
        baseUrl = "https://pokeapi.co/api/v2/pokemon/";
        restClient = restClientBuilder.baseUrl(baseUrl).build();
    }

    public void getAllPokemon() {
        List<PokeApiDto> allPokemon = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        for (int i = 1; i <= totalPokemon; i++) {
            allPokemon.add(getPokemon(i));
        }

        objectMapper.writeValue(Paths.get("pokemon.json").toFile(), allPokemon);
    }

    public PokeApiDto getPokemon(int number) {
        return restClient.get().uri(baseUrl + number).retrieve().body(PokeApiDto.class);
    }
}