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

import com.pokemon_API.models.dto.WeaknessDTO;

import com.pokemon_API.models.entitys.Weakness;
import com.pokemon_API.models.repository.WeaknessRepository;

@RestController
@CrossOrigin("*")
@RequestMapping ("/Weakness")
public class WeaknessController {
    @Autowired
    WeaknessRepository weaknessRepository;
    
    @GetMapping
    public ResponseEntity<Object> listAll(){
        return ResponseEntity.status(HttpStatus.OK).body(weaknessRepository.findAll());
    }
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody WeaknessDTO weaknesseDTO){
        Weakness weaknessEntity = new Weakness();
        BeanUtils.copyProperties(weaknesseDTO,weaknessEntity);

        return ResponseEntity.status(HttpStatus.OK).body(weaknessRepository.save(weaknessEntity));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody WeaknessDTO weaknessDTO){
        Optional<Weakness> weaknessExists = weaknessRepository.findById(id);
        if(!weaknessExists.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Weakness not found");
        }
        Weakness weakEntity = weaknessExists.get();
        BeanUtils.copyProperties(weaknessDTO, weakEntity);

        return ResponseEntity.status(HttpStatus.OK).body(weaknessRepository.save(weakEntity));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id, @RequestBody WeaknessDTO weaknessDTO){
        Optional<Weakness> weaknessExistis = weaknessRepository.findById(id);
        if(!weaknessExistis.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Weakness not found");
        }
        weaknessRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Success");

    }
    
}
