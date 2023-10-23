package com.pokemon_API.models.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pokemon_API.models.entitys.Pokemon;
import com.pokemon_API.models.entitys.PokemonType;

@Repository
public interface PokemonRepository extends CrudRepository<Pokemon, Integer>{
   
    List<Pokemon> findByTypesIn(List<PokemonType> types);

}
