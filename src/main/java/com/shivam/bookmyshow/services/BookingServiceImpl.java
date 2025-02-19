package com.shivam.bookmyshow.services;

import com.shivam.bookmyshow.exceptions.InvalidShowSeatException;
import com.shivam.bookmyshow.exceptions.ShowSeatNotAvailableException;
import com.shivam.bookmyshow.exceptions.UserNotFoundException;
import com.shivam.bookmyshow.models.*;
import com.shivam.bookmyshow.repositories.BookingRepository;
import com.shivam.bookmyshow.repositories.UserRepository;
import com.shivam.bookmyshow.utils.ShowSeatUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private UserRepository userRepository;
    private BookingRepository bookingRepository;
    private ShowSeatUtils showSeatUtils;

    public BookingServiceImpl(UserRepository userRepository,
                              BookingRepository bookingRepository,
                              ShowSeatUtils showSeatUtils) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.showSeatUtils = showSeatUtils;
    }

    @Override
    public Booking bookTicket(long userId, List<Long> showSeatIds)
            throws UserNotFoundException, InvalidShowSeatException, ShowSeatNotAvailableException
    {
        // check userid is valid or not
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User Not Found"));

        List<ShowSeat> showSeats = showSeatUtils.checkAndBlockedShowSeats(showSeatIds);

        // calculate price
        int amount = 0;
        List<ShowSeatType> showSeatTypes = showSeats.get(0).getShow().getShowSeatTypes();
        for (ShowSeat showSeat : showSeats) {
            for (ShowSeatType showSeatType : showSeatTypes) {
                if (showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
                    amount += showSeatType.getPrice();
                    break;
                }
            }
        }

        // create a booking and return
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setBookingTime(new Date());
        booking.setShowSeats(showSeats);
        booking.setAmount(amount);


        return bookingRepository.save(booking);
    }
}
