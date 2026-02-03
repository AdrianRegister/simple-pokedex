package com.example.pokemon.pokeapi;

public record PokeApiDto(
    int id,
    String name,
    int height,
    int weight,
    int base_experience
) {}
