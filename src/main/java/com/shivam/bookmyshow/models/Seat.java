package com.shivam.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Seat extends BaseModel{
    private String seatNo;
    private SeatType seatType;
    private int row;
    private int col;
}
