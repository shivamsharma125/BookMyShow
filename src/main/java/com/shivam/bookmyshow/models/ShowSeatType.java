package com.shivam.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "show_seat_types")
public class ShowSeatType extends BaseModel{
    @ManyToOne
    private Show show; // [M:1]
    @ManyToOne
    private SeatType seatType; // [M:1]
    private int price;
}
