package com.pokemon_API.models.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PokemonDTO {
    private String name;
    private Integer weight;
    private Integer height;
    private Integer hp;
    private Integer attack; 
    private Integer defense;
    private Integer s_Attack; 
    private Integer s_defence;
    private Integer speed;
    private List<Integer> types;
    private List<Integer> weaknesses;
    private List<Integer> skills;
    private Integer species_id;
}
