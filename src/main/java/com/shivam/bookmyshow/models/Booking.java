package com.shivam.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "bookings")
public class Booking extends BaseModel{
    @ManyToOne
    private User user; // [M:1]
    private Date bookingTime;
//    private Show show;
    @ManyToMany
    private List<ShowSeat> showSeats; // [M:M] show seat can be part of a cancelled booking
    private int amount;
    @OneToMany
    @JoinColumn(name = "booking_id")
    private List<Payment> payments; // [1:M]
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
}
