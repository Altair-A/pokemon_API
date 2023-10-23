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

import com.pokemon_API.models.dto.PokemonTypeDTO;
import com.pokemon_API.models.entitys.PokemonType;
import com.pokemon_API.models.repository.PokemonTypeRepository;

@RestController
@CrossOrigin("*")
@RequestMapping ("/Types")
public class PokemonTypeController {
    @Autowired
    PokemonTypeRepository pokemonTypeRepository; 

    @GetMapping
    public ResponseEntity<Object> listAll(){
        return ResponseEntity.status(HttpStatus.OK).body(pokemonTypeRepository.findAll()); 
    }
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody PokemonTypeDTO pokemonTypeDTO){
        PokemonType typeEntity = new PokemonType();
        BeanUtils.copyProperties(pokemonTypeDTO, typeEntity);

        return ResponseEntity.status(HttpStatus.OK).body(pokemonTypeRepository.save(typeEntity));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody PokemonTypeDTO pokemonTypeDTO){
        Optional<PokemonType> typeExists = pokemonTypeRepository.findById(id);
        if(!typeExists.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Type not found");
        }
        PokemonType typeEntity = typeExists.get();
        BeanUtils.copyProperties(pokemonTypeDTO, typeEntity);

        return ResponseEntity.status(HttpStatus.OK).body(pokemonTypeRepository.save(typeEntity));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delet(@PathVariable Integer id){
        Optional<PokemonType> typeExists = pokemonTypeRepository.findById(id);
        if(!typeExists.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Type not found");
        }

        pokemonTypeRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
