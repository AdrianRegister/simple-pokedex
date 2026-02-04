package com.example.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import tools.jackson.core.type.TypeReference;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.pokemon.pokeapi.PokeApi;
import com.example.pokemon.pokeapi.PokeApiDto;

import tools.jackson.databind.ObjectMapper;

public class ApiServiceTest {
    
    @Mock
    private PokeApi pokeApi;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ApiService apiService;

    private List<PokeApiDto> mockData = List.of(
        new PokeApiDto(1, "bulbasaur", 7, 69, 64),
        new PokeApiDto(2, "ivysaur", 10, 130, 142),
        new PokeApiDto(3, "venusaur", 20, 1000, 236),
        new PokeApiDto(4, "charmander", 6, 85, 62),
        new PokeApiDto(5, "charmeleon", 11, 190, 142)
    );

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getHeaviestTest() throws Exception {
        when(objectMapper.readValue(
                any(InputStream.class),
                ArgumentMatchers.<TypeReference<List<PokeApiDto>>>any())).thenReturn(mockData);

        List<PokeApiDto> result = apiService.getHeaviest();

        assertEquals(5, result.size());
        assertEquals("venusaur", result.get(0).name());
        assertEquals(1000, result.get(0).weight());
        assertEquals("bulbasaur", result.get(4).name());
        assertEquals(69, result.get(4).weight());
    }
    
    @Test
    void getTallestTest() throws Exception {
        when(objectMapper.readValue(
                any(InputStream.class),
                ArgumentMatchers.<TypeReference<List<PokeApiDto>>>any())).thenReturn(mockData);

        List<PokeApiDto> result = apiService.getTallest();

        assertEquals(5, result.size());
        assertEquals("venusaur", result.get(0).name());
        assertEquals(20, result.get(0).height());
        assertEquals("charmander", result.get(4).name());
        assertEquals(6, result.get(4).height());
    }
    
    @Test
    void getMostExperienceTest() throws Exception {
        when(objectMapper.readValue(
                any(InputStream.class),
                ArgumentMatchers.<TypeReference<List<PokeApiDto>>>any())).thenReturn(mockData);

        List<PokeApiDto> result = apiService.getMostExperience();

        assertEquals(5, result.size());
        assertEquals("venusaur", result.get(0).name());
        assertEquals(236, result.get(0).base_experience());
        assertEquals("charmander", result.get(4).name());
        assertEquals(62, result.get(4).base_experience());
    }
    
    
    @Test
    void getAllPokemonTest() {
        apiService.getAllPokemon();
        verify(pokeApi, times(1)).getAllPokemon();
    }
}
