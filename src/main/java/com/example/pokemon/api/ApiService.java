package com.example.pokemon.api;

import java.nio.file.Files;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.List;
import java.nio.file.Path;

import org.springframework.stereotype.Service;

import com.example.pokemon.pokeapi.PokeApi;
import com.example.pokemon.pokeapi.PokeApiDto;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Service
public class ApiService {
    
    private final PokeApi pokeApi;
    private final ObjectMapper objectMapper;

    public ApiService(PokeApi pokeApi, ObjectMapper objectMapper) {
        this.pokeApi = pokeApi;
        this.objectMapper = objectMapper;
    }

    public List<PokeApiDto> readFromJsonFile() {
        try {
            return objectMapper.readValue(
                    Files.newInputStream(Path.of("pokemon.json")),
                    new TypeReference<List<PokeApiDto>>() {}
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to read pokemon.json", e);
        }
    }
    
    public List<PokeApiDto> getHeaviest() {
        return readFromJsonFile()
            .stream()
            .sorted(Comparator.comparingInt(PokeApiDto::weight).reversed())
            .limit(5)
            .collect(Collectors.toList());
    }

    public List<PokeApiDto> getTallest() {
        return readFromJsonFile()
            .stream()
            .sorted(Comparator.comparingInt(PokeApiDto::height).reversed())
            .limit(5)
            .collect(Collectors.toList());
    }

    public List<PokeApiDto> getMostExperience() {
        return readFromJsonFile()
            .stream()
            .sorted(Comparator.comparingInt(PokeApiDto::base_experience).reversed())
            .limit(5)
            .collect(Collectors.toList());
    }

    public void getAllPokemon() {
        this.pokeApi.getAllPokemon();
    }
}
