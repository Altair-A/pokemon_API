package com.pokemon_API.models.entitys;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="type")
public class PokemonType {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private Integer id;
    private String  type;

    @JsonBackReference
    @ManyToMany(targetEntity = Pokemon.class, mappedBy = "types", cascade = CascadeType.ALL)
    private List<Pokemon> pokemons;
}
    

 
