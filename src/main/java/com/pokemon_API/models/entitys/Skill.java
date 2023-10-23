package com.pokemon_API.models.entitys;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String skill;
    private String description;

    @JsonBackReference
    @ManyToMany(targetEntity = Pokemon.class, mappedBy = "skills", cascade = CascadeType.ALL)
    private List<Pokemon> pokemons;
}
