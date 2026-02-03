package com.example.pokemon.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private final ApiService service;

    public ApiController(ApiService service) {
        this.service = service;
    }

    @RequestMapping("/init")
    public void getAllPokemon() {
        this.service.getAllPokemon();
    }
}
