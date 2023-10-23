package com.pokemon_API.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pokemon_API.models.entitys.Pokeball;

@Repository
public interface PokeballRepository extends CrudRepository<Pokeball,Integer> {
    
    @Query("SELECT p.name, COUNT(p.name) FROM Pokeball AS p GROUP BY p.name ORDER BY p.name DESC")
    List<Object[]> coutToame();
    //SELECT pk.name, COUNT(pk.name) FROM Pokeball AS p JOIN pokemon AS pk ON p.pokemon_id = pk.id GROUP BY pk.name
}
