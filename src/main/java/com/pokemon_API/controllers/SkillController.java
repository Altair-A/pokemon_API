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

import com.pokemon_API.models.dto.SkillDTO;
import com.pokemon_API.models.entitys.Skill;
import com.pokemon_API.models.repository.SkillRepository;

@RestController
@CrossOrigin("*")
@RequestMapping ("/Skills")
public class SkillController {
    @Autowired
    SkillRepository skillRepository;

    @GetMapping
    public ResponseEntity<Object> listAll(){
        return ResponseEntity.status(HttpStatus.OK).body(skillRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody SkillDTO skillDTO){
        Skill skillEntity = new Skill();
        BeanUtils.copyProperties(skillDTO, skillEntity);
        
        return ResponseEntity.status(HttpStatus.OK).body(skillRepository.save(skillEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody SkillDTO skillDTO){
        Optional<Skill> skillExists = skillRepository.findById(id);
        if(!skillExists.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Skill not found");
        }
        Skill skillEntity = skillExists.get();
        BeanUtils.copyProperties(skillDTO, skillEntity);
        
        return ResponseEntity.status(HttpStatus.OK).body(skillRepository.save(skillEntity)); 
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id, @RequestBody SkillDTO skillDTO){
        Optional<Skill> skillExists = skillRepository.findById(id);
        if(!skillExists.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Skill not found");
        }
        skillRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Sucess");
    }
}
