package com.shivam.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "show_seats")
public class ShowSeat extends BaseModel{
    @ManyToOne
    private Show show; // [M:1]
    @ManyToOne
    private Seat seat; // [M:1]
    @Enumerated(EnumType.ORDINAL)
    private SeatStatus seatStatus;
}
