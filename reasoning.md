# Readme

## PokeApi

I placed everything relating to the external Pokemon API in its own singleton service. This maintains separations of concerns between my app and its external dependencies.

## App

My app consists of a simple, single controller and service. There is no repository layer as nothing is persisted. However, there is local caching. This is because the app will always return the same information, and given the long processing time in acquiring the information initially, it does not make sense to continuously repeat this call every single time.

## DTO

Each endpoint returns the data from the Pokemon API mapped via a DTO, only containing the fields we care about: name, height, weight and base experience.
