package com.shivam.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "seats")
public class Seat extends BaseModel{
    private String seatNo;
    @ManyToOne
    private SeatType seatType; // [M:1]
    private int rowVal;
    private int colVal;
}
