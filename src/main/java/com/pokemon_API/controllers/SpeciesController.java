package com.pokemon_API.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon_API.models.dto.SpeciesDTO;
import com.pokemon_API.models.entitys.Species;
import com.pokemon_API.models.repository.SpeciesRpository;

@RestController
@CrossOrigin("*")
@RequestMapping("/Species")
public class SpeciesController {
    @Autowired
    SpeciesRpository speciesRpository;

    
    @GetMapping
    public ResponseEntity<Object> listAll(){
        return ResponseEntity.status(HttpStatus.OK).body(speciesRpository.findAll());
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody SpeciesDTO speciesDTO){
        Species speciesEntity = new Species();
        BeanUtils.copyProperties(speciesDTO, speciesEntity);
        
        return ResponseEntity.status(HttpStatus.OK).body(speciesRpository.save(speciesEntity));
    }
}
