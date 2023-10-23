package com.pokemon_API.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.pokemon_API.models.entitys.Trainner;
public interface TrainnerRepository  extends CrudRepository<Trainner, Integer>{
    
}
