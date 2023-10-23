package com.pokemon_API.models.entitys;


import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Pokemon")
public class Pokemon {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer weight;
    private Integer height;
    private Integer hp;
    private Integer attack; 
    private Integer defense;
    private Integer s_Attack; 
    private Integer s_defence;
    private Integer speed;
    
    @JsonManagedReference
    @ManyToMany(targetEntity = PokemonType.class, cascade = CascadeType.ALL )
    private List<PokemonType> types;

    @JsonManagedReference
    @ManyToMany (targetEntity = Weakness.class, cascade = CascadeType.ALL)
    private List<Weakness> weaknesses;
    
    @JsonManagedReference
    @ManyToMany (targetEntity = Skill.class, cascade = CascadeType.ALL)
    private List<Skill> skills;

    @JsonBackReference
    @OneToOne(mappedBy = "pokemon")
    private Pokeball pokeball;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="species_id")
    private Species species;
}
