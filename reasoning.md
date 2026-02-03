# Readme

## PokeApi

I placed everything relating to the external Pokemon API in its own singleton service. This maintains separations of concerns between my app and its external dependencies.

## App

My app consists of a simple, single controller and service. There is no repository layer as nothing is persisted.

## DTO

Each endpoint returns the data from the Pokemon API mapped via a DTO, only containing the fields we care about: name, height, weight and base experience.

## Performance

No caching, /init endpoint to populate json file that serves as pseudo-database
