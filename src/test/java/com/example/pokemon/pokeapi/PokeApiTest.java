package com.example.pokemon.pokeapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestClient;

public class PokeApiTest {
    
    @Mock
    private RestClient.Builder restClientBuilder;

    @Mock
    private RestClient restClient;

    @Mock
    private RestClient.RequestHeadersUriSpec requestSpec;

    @Mock
    private RestClient.RequestHeadersSpec headersSpec;

    @Mock
    private RestClient.ResponseSpec responseSpec;

    private PokeApi pokeApi;

    private final PokeApiDto mockDto = new PokeApiDto(1, "bulbasaur", 7, 69, 64);

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        when(restClientBuilder.baseUrl(anyString())).thenReturn(restClientBuilder);
        when(restClientBuilder.build()).thenReturn(restClient);

        when(restClient.get()).thenReturn(requestSpec);
        when(requestSpec.uri(anyString())).thenReturn(headersSpec);
        when(headersSpec.retrieve()).thenReturn(responseSpec);

        pokeApi = new PokeApi(restClientBuilder);
    }

    @Test
    void getPokemonTest() {
        when(responseSpec.body(PokeApiDto.class)).thenReturn(mockDto);

        PokeApiDto result = pokeApi.getPokemon(1);
        assertEquals(1, result.id());
        assertEquals("bulbasaur", result.name());
        assertEquals(7, result.height());
        assertEquals(69, result.weight());
        assertEquals(64, result.base_experience());
    }

    @Test
    void getAllPokemonTest() throws Exception {
        PokeApi spyApi = spy(pokeApi);
        doReturn(mockDto).when(spyApi).getPokemon(anyInt());

        spyApi.getAllPokemon();
        verify(spyApi, atLeast(5)).getPokemon(anyInt());
    }
}
