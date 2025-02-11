package com.shivam.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "screens")
public class Screen extends BaseModel{
    private String screenNo;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;
    @OneToMany
    @JoinColumn(name = "screen_id")
    private List<Seat> seats; // [1:M]
    @ManyToOne
    private Theatre theatre; // [M:1]
}
