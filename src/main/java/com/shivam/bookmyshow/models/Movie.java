package com.shivam.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Movie extends BaseModel{
    private String name;
    private int duration;
    private List<String> actors;
    private float rating;
    private List<Feature> features;
    private List<Language> languages;
}
