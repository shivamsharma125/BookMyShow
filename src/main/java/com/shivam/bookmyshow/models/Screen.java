package com.shivam.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Screen extends BaseModel{
    private String screenNo;
    private List<Feature> features;
    private List<Seat> seats;
    private Theatre theatre;
}
