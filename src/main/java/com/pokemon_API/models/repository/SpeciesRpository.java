package com.pokemon_API.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.pokemon_API.models.entitys.Species;

public interface SpeciesRpository extends CrudRepository<Species, Integer> {
    
}
