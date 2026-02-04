package com.example.pokemon.api;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.pokemon.pokeapi.PokeApiDto;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ApiService apiService;

    @Test
    void initTest() throws Exception {
        mockMvc.perform(get("/init"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("""
                            {
                                "response": "ok"
                            }
                        """));
    }
    
    @Test
    void pokemonTestInvalidRequestParam() throws Exception {
        mockMvc.perform(get("/pokemon").param("filter", "unknown"))
            .andExpect(status().isBadRequest());
    }

    
    @Test 
    void getHeaviestPokemonTest() throws Exception {
        when(apiService.getHeaviest())
            .thenReturn(List.of(
                new PokeApiDto(790, "cosmoem", 1, 9999, 140),
                new PokeApiDto(797, "celesteela", 92, 9999, 257),
                new PokeApiDto(383, "groudon", 35, 9500, 302),
                new PokeApiDto(890, "eternatus", 200, 9500, 345),
                new PokeApiDto(750, "mudsdale", 25, 9200, 175)
            ));

        mockMvc.perform(get("/pokemon").param("filter", "heaviest"))
            .andExpect(jsonPath("$.length()").value(5))
            .andExpect(jsonPath("$[0].name").value("cosmoem"))
            .andExpect(jsonPath("$[4].weight").value(9200));
    }


    @Test 
    void getTallestPokemonTest() throws Exception {
        when(apiService.getTallest())
            .thenReturn(List.of(
                new PokeApiDto(890, "eternatus", 200, 9500, 345),
                new PokeApiDto(321, "wailord", 145, 3980, 175),
                new PokeApiDto(977, "dondozo", 120, 2200, 265),
                new PokeApiDto(208, "steelix", 92, 4000, 179),
                new PokeApiDto(797, "celesteela", 92, 9999, 257)
            ));

        mockMvc.perform(get("/pokemon").param("filter", "tallest"))
            .andExpect(jsonPath("$.length()").value(5))
            .andExpect(jsonPath("$[3].name").value("steelix"))
            .andExpect(jsonPath("$[1].height").value(145));
    }
    
    @Test
    void getMostExperiencePokemonTest() throws Exception {
        when(apiService.getMostExperience())
            .thenReturn(List.of(
                new PokeApiDto(242, "blissey", 15, 468, 608),
                new PokeApiDto(113, "chansey", 11, 346, 395),
                new PokeApiDto(531, "audino", 11, 310, 390),
                new PokeApiDto(890, "eternatus", 200, 9500, 345),
                new PokeApiDto(888, "zacian", 28, 1100, 335)
            ));

        mockMvc.perform(get("/pokemon").param("filter", "mostexperience"))
            .andExpect(jsonPath("$.length()").value(5))
            .andExpect(jsonPath("$[4].name").value("zacian"))
            .andExpect(jsonPath("$[0].base_experience").value(608));
    }
}
