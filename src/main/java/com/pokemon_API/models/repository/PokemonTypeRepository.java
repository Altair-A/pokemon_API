package com.pokemon_API.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.pokemon_API.models.entitys.PokemonType;


public interface PokemonTypeRepository extends CrudRepository<PokemonType, Integer>{
    
    PokemonType findByType(String type);
}
