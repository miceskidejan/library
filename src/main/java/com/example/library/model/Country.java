package com.example.library.model;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Data
@Entity
public class Country {

    @Id
    private Long id;

    private String name;

    private String continent;

    public Country(){
    }

    public Country(String name, String continent){
        this.name=name;
        this.continent=continent;
    }
}
