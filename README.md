# Readme

## App

The app consists of a simple, single controller and service. There is no repository layer as nothing is persisted.

### DTO

The data returned by the app has been transformed from the raw Pokemon API response via a DTO, only containing the fields we care about: id, name, height, weight and base experience.
If more fields from the raw response are needed, these fields can be added to the DTO on an individual basis. No other code needs to be touched. However, the `/init` endpoint will need to be called again.

### PokeAPI restrictions

PokeAPI v2 does not provide filtering of results via query parameters (their GraphQL API offers this, but is outside of this task's scope). This means that a solution is needed to avoid making over 1,000 API calls every time the app endpoint is hit - either in-memory caching (Spring Boot offers this natively) or through a pseudo-database using a JSON file. I decided to implement the pseudo-database, having decided that this best mirrors a real-life production application. In-memory caching would be more suitable for caching my app's own endpoint responses.

### Endpoints

**GET** `/init`

This endpoint calls PokeAPI v2 in a loop, populating a JSON file with all 1025 current Pokemon, thus providing pseudo-persistence to the app. In the case of new Pokemon being added, the method should be called again.

**GET** `/pokemon`

Query parameter: filter <br>
Accepted parameter values: heaviest | tallest | mostexperience

Example usage (JavaScript):

```javascript
async function getHeaviestPokemon() {
  const response = await fetch('http://localhost:8080/pokemon?filter=heaviest');
  const result = await response.json();
  return result;
}
```

Example response (JSON)

```json
[
  {
    "id": 790,
    "name": "cosmoem",
    "height": 1,
    "weight": 9999,
    "base_experience": 140
  },
  {
    "id": 797,
    "name": "celesteela",
    "height": 92,
    "weight": 9999,
    "base_experience": 257
  },
  {
    "id": 383,
    "name": "groudon",
    "height": 35,
    "weight": 9500,
    "base_experience": 302
  },
  {
    "id": 890,
    "name": "eternatus",
    "height": 200,
    "weight": 9500,
    "base_experience": 345
  },
  {
    "id": 750,
    "name": "mudsdale",
    "height": 25,
    "weight": 9200,
    "base_experience": 175
  }
]
```

## PokeApi

I placed everything relating to PokeAPI v2 in its own singleton service. This maintains separations of concerns between my app and its external dependency.

## Tests

Having never used junit / Spring Boot testing before, I heavily relied on AI assistance for this section. I have attempted to apply my own knowledge of best practices testing and test coverage to them.

## Next steps...

In order to make the app production-ready, the following features could be considered:

- Integration tests between the controller and services
- Full E2E test between the app: controller -> service -> PokeAPI
- In-memory caching of app responses
- Rate limiting
