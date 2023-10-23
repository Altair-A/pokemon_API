package com.pokemon_API.models.repository;

import org.springframework.data.repository.CrudRepository;
import com.pokemon_API.models.entitys.Skill;

public interface SkillRepository extends CrudRepository<Skill,Integer>{
    
}
