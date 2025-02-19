package com.shivam.bookmyshow.services;

import com.shivam.bookmyshow.exceptions.InvalidShowSeatException;
import com.shivam.bookmyshow.exceptions.ShowSeatNotAvailableException;
import com.shivam.bookmyshow.exceptions.UserNotFoundException;
import com.shivam.bookmyshow.models.Booking;

import java.util.List;

public interface BookingService {
    Booking bookTicket(long userId, List<Long> showSeatIds) throws UserNotFoundException, InvalidShowSeatException, ShowSeatNotAvailableException;
}
