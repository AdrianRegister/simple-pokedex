package com.example.pokemon.api;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pokemon.pokeapi.PokeApiDto;

@RestController
public class ApiController {

    private final ApiService service;

    public ApiController(ApiService service) {
        this.service = service;
    }

    @RequestMapping("")
    public List<PokeApiDto> getHeaviest() {
        return this.service.getAll();
    }
}
