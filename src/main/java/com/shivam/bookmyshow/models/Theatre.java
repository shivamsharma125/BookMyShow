package com.shivam.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "theatres")
public class Theatre extends BaseModel{
    private String name;
    private String address;
    @OneToMany(mappedBy = "theatre")
    private List<Screen> screens; // [1:M]
    @ManyToOne
    private City city; // [M:1]
}
