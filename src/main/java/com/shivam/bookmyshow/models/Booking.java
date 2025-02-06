package com.shivam.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Booking extends BaseModel{
    private User user;
    private Date bookingTime;
    private Show show;
    private List<ShowSeat> showSeats;
    private int amount;
    private List<Payment> payments;
    private BookingStatus bookingStatus;
}
