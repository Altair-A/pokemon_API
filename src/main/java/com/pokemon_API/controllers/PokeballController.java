package com.pokemon_API.controllers;


import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon_API.models.dto.PokeballDTO;
import com.pokemon_API.models.entitys.Pokeball;
import com.pokemon_API.models.entitys.Pokemon;
import com.pokemon_API.models.repository.PokeballRepository;
import com.pokemon_API.models.repository.PokemonRepository;

@RestController
@CrossOrigin("*")
@RequestMapping ("/Pokeballs")
public class PokeballController {
    @Autowired
    PokeballRepository pokeballRepository;

    @Autowired
    PokemonRepository pokemonRepository;
    
    @GetMapping
    public ResponseEntity<Object> listAll(){
        return ResponseEntity.status(HttpStatus.OK).body(pokeballRepository.findAll());
    }

    @GetMapping("/Pokemons")
    public ResponseEntity<Object> listPokemons(){
        return ResponseEntity.status(HttpStatus.OK).body(pokeballRepository.coutToame());
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody PokeballDTO pokeballDTO) {
        Pokeball pokeballEntitity = new Pokeball();

        Integer pokemon_id = pokeballDTO.getPokemon_id();
        Optional<Pokemon> pokemon = pokemonRepository.findById(pokemon_id);
        if(pokemon.isPresent()){
            pokeballEntitity.setPokemon(pokemon.get());
        }
        
        
        BeanUtils.copyProperties(pokeballDTO, pokeballEntitity);
        return ResponseEntity.status(HttpStatus.OK).body(pokeballRepository.save(pokeballEntitity));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody PokeballDTO pokeballDTO){
        Optional<Pokeball> pokebalExists = pokeballRepository.findById(id);
        if(!pokebalExists.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokeball not found");
        }
        Pokeball pokeballEntity = pokebalExists.get();
        BeanUtils.copyProperties(pokebalExists, pokeballEntity);
        
        return ResponseEntity.status(HttpStatus.OK).body(pokeballRepository.save(pokeballEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        Optional<Pokeball> pokebalExists = pokeballRepository.findById(id);
        if(!pokebalExists.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokeball not found");
        }
        pokeballRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
    
}
