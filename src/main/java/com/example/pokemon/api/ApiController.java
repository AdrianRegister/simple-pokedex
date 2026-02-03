package com.example.pokemon.api;

import java.util.List;

import com.example.pokemon.pokeapi.PokeApiDto;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private final ApiService service;

    public static enum FilterCategory {
        heaviest,
        tallest,
        mostexperience
    }

    public ApiController(ApiService service) {
        this.service = service;
    }

    @RequestMapping("/init")
    public Response getAllPokemon() {
        this.service.getAllPokemon();
        return new Response("ok");
    }

    @RequestMapping("/pokemon")
    public List<PokeApiDto> getPokemonByCategory(@RequestParam FilterCategory filter) {
        return switch (filter) {
            case heaviest -> this.service.getHeaviest();
            case tallest -> this.service.getTallest();
            case mostexperience -> this.service.getMostExperience();
        };
    }
}
