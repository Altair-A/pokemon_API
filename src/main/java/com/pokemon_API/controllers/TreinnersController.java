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


import com.pokemon_API.models.dto.TrainnerDTO;
import com.pokemon_API.models.entitys.Pokeball;
import com.pokemon_API.models.entitys.Trainner;
import com.pokemon_API.models.repository.PokeballRepository;
import com.pokemon_API.models.repository.TrainnerRepository;

@RestController
@CrossOrigin("*")
@RequestMapping ("/Trainners")
public class TreinnersController {
    @Autowired
    TrainnerRepository trainnerReposotory;

    @Autowired
    PokeballRepository pokeballRepository;
    
    @GetMapping
    public ResponseEntity<Object> listAll(){
        return ResponseEntity.status(HttpStatus.OK).body(trainnerReposotory.findAll()); 
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody TrainnerDTO trainnerDTO){
        Trainner trainnerEntity = new Trainner();
        for(Integer pokeballs : trainnerDTO.getPokeballs() ){
            Optional<Pokeball> pokeballTrainner = pokeballRepository.findById(pokeballs);
            if(!pokeballTrainner.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokeball not found");
            }
            trainnerEntity.getPokeballs().add(pokeballTrainner.get());
            pokeballTrainner.get().setTreinner(trainnerEntity);
        }
        
       
        BeanUtils.copyProperties(trainnerDTO, trainnerEntity);

        return ResponseEntity.status(HttpStatus.OK).body(trainnerReposotory.save(trainnerEntity));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody TrainnerDTO trainnerDTO){
        Optional<Trainner> trainnerExists = trainnerReposotory.findById(id);
        if(!trainnerExists.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Type not found");
        }
        Trainner trainnerEntity = trainnerExists.get();
        BeanUtils.copyProperties(trainnerDTO, trainnerEntity);

        return ResponseEntity.status(HttpStatus.OK).body(trainnerReposotory.save(trainnerEntity));
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        Optional<Trainner> trainnerExists = trainnerReposotory.findById(id);
        if(!trainnerExists.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trainer not found");
        }

        trainnerReposotory.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
