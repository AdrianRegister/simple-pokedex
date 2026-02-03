package com.example.pokemon.pokeapi;

public record PokeApiDto(
    String name,
    int height,
    int weight,
    int base_experience
) {}
