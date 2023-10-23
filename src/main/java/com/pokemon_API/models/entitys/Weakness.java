package com.pokemon_API.models.entitys;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="weakness")
public class Weakness {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String weakness;


    @JsonBackReference
    @ManyToMany(targetEntity = Pokemon.class, mappedBy = "weaknesses", cascade = CascadeType.ALL)
    private List<Pokemon> pokemons;
}
