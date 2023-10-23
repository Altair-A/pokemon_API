package com.pokemon_API.controllers;


import java.util.ArrayList;

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

import com.pokemon_API.models.dto.PokemonDTO;
import com.pokemon_API.models.entitys.Pokemon;
import com.pokemon_API.models.entitys.PokemonType;
import com.pokemon_API.models.entitys.Skill;
import com.pokemon_API.models.entitys.Species;
import com.pokemon_API.models.entitys.Weakness;
import com.pokemon_API.models.repository.*;



@RestController
@CrossOrigin("*")
@RequestMapping ("/Pokemons")
public class PokemonController {
    @Autowired
    PokemonRepository pokemonRepository; 

    @Autowired
    PokemonTypeRepository pokemonTypeRepository; 

    @Autowired
    WeaknessRepository weaknessRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    SpeciesRpository speciesRpository;

    // @Autowired
    // CustonRepository custonRepository;

    @GetMapping
    public ResponseEntity<Object> listAll(){
        return ResponseEntity.status(HttpStatus.OK).body(pokemonRepository.findAll()); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> listById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(pokemonRepository.findById(id)); 
    }
    @GetMapping("/Type/{typeName}")
    public ResponseEntity<Object> listType(@PathVariable String typeName){
        PokemonType type = pokemonTypeRepository.findByType(typeName);
        ArrayList<PokemonType> typess = new ArrayList<PokemonType>();
        typess.add(type);
        return ResponseEntity.status(HttpStatus.OK).body(pokemonRepository.findByTypesIn(typess) ); 
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody PokemonDTO pokemonDTO){
        Pokemon newPokemon = new Pokemon();
        

         // checar e adicionar os types ao Pokemon
         ArrayList<PokemonType> typess = new ArrayList<PokemonType>();
         for(Integer types: pokemonDTO.getTypes() ){
            Optional<PokemonType> typePokemon = pokemonTypeRepository.findById(types);
            if(!typePokemon.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Type not found");
            }
            typess.add(typePokemon.get());
        }
        newPokemon.setTypes(typess);

        //checar e adicionar Weakness ao pokemon
        ArrayList<Weakness> wkns = new ArrayList<Weakness>();
        for(Integer weaknesses: pokemonDTO.getWeaknesses()){
            Optional<Weakness> weaknessPokemon = weaknessRepository.findById(weaknesses);
            if(!weaknessPokemon.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("weakness not found");
            }
            wkns.add(weaknessPokemon.get());
        }
        newPokemon.setWeaknesses(wkns);

        //checar e adicionar Skills ao Pokemon
        ArrayList<Skill> skl = new ArrayList<Skill>();
          for(Integer skill: pokemonDTO.getSkills()){
            Optional<Skill> skillPokemon = skillRepository.findById(skill);
            if(!skillPokemon.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("skill not found");
            }
            skl.add(skillPokemon.get());
        }
        newPokemon.setSkills(skl);

        //checar e adicionar Species ao Pokemon
        Integer species_id = pokemonDTO.getSpecies_id();
        Optional<Species> species = speciesRpository.findById(species_id);
        if(species.isPresent()){
            newPokemon.setSpecies(species.get());
        }
        
        BeanUtils.copyProperties(pokemonDTO, newPokemon);
        return ResponseEntity.status(HttpStatus.OK).body(pokemonRepository.save(newPokemon));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody PokemonDTO pokemonDTO){
        Optional<Pokemon> pokemonExists = pokemonRepository.findById(id);
        if(!pokemonExists.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokemon not found");
        }
        Pokemon updatePokemon = pokemonExists.get();
        

        //checar e adicionar os types ao Pokemon
        ArrayList<PokemonType> typess = new ArrayList<PokemonType>();
        for(Integer types: pokemonDTO.getTypes() ){
            Optional<PokemonType> typePokemon = pokemonTypeRepository.findById(types);
            if(!typePokemon.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Type not found");
            }
            typess.add(typePokemon.get());
            
        }
        updatePokemon.setTypes(typess);

          //checar e adicionar Weakness ao pokemon
        ArrayList<Weakness> wkns = new ArrayList<Weakness>();
        for(Integer weaknesses: pokemonDTO.getWeaknesses()){
            Optional<Weakness> weaknessPokemon = weaknessRepository.findById(weaknesses);
            if(!weaknessPokemon.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("weakness not found");
            }
            wkns.add(weaknessPokemon.get());
        }
        updatePokemon.setWeaknesses(wkns);

        //checar e adicionar Skills ao Pokemon
        ArrayList<Skill> skl = new ArrayList<Skill>();
        for(Integer skill: pokemonDTO.getSkills()){
            Optional<Skill> skillPokemon = skillRepository.findById(skill);
           if(!skillPokemon.isPresent()){
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body("skill not found");
           }
           skl.add(skillPokemon.get());
       }
        updatePokemon.setSkills(skl);
       
        //checar e adicionar Species ao Pokemon
        Integer species_id = pokemonDTO.getSpecies_id();
        Optional<Species> species = speciesRpository.findById(species_id);
        if(species.isPresent()){
            updatePokemon.setSpecies(species.get());
        }

        BeanUtils.copyProperties(pokemonDTO, updatePokemon);
        return ResponseEntity.status(HttpStatus.OK).body(pokemonRepository.save(updatePokemon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delet(@PathVariable Integer id){
        Optional<Pokemon> pokemonExists = pokemonRepository.findById(id);
        if(!pokemonExists.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokemon not found");
        }

        pokemonRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
