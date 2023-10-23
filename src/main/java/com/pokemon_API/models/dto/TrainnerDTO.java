package com.pokemon_API.models.dto;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainnerDTO {
    private String name;
    private Integer xp;
    private List<Integer> pokeballs;
    
}
