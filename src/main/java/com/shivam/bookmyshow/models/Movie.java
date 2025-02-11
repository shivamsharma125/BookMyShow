package com.shivam.bookmyshow.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "movies")
public class Movie extends BaseModel{
    private String name;
    private int duration;
    @ElementCollection
    private List<String> actors;
    private float rating;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Language> languages;
}
