package com.shivam.bookmyshow.services;

import com.shivam.bookmyshow.exceptions.InvalidShowSeatException;
import com.shivam.bookmyshow.exceptions.ShowSeatNotAvailableException;
import com.shivam.bookmyshow.exceptions.UserNotFoundException;
import com.shivam.bookmyshow.models.*;
import com.shivam.bookmyshow.repositories.BookingRepository;
import com.shivam.bookmyshow.repositories.ShowSeatRepository;
import com.shivam.bookmyshow.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
    private UserRepository userRepository;
    private BookingRepository bookingRepository;
    private ShowSeatRepository showSeatRepository;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookTicket(long userId, List<Long> showSeatIds)
            throws UserNotFoundException, InvalidShowSeatException, ShowSeatNotAvailableException
    {
        // check userid is valid or not
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User Not Found"));

        // check showseats valid or not
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);
        if (showSeats.size() != showSeatIds.size()){
            Set<Long> validShowSeatIds = showSeats.stream()
                    .map(ShowSeat::getId)
                    .collect(Collectors.toSet());
            List<Long> invalidShowSeatIds = showSeatIds.stream()
                    .filter(id -> !validShowSeatIds.contains(id))
                    .toList();
            throw new InvalidShowSeatException("invalid show seat ids : " + invalidShowSeatIds);
        }

        // check all showseats available or not
        List<Long> unavailableShowSeatIds = showSeats.stream()
                .filter(showSeat -> !showSeat.getSeatStatus().equals(SeatStatus.AVAILABLE))
                .map(ShowSeat::getId)
                .toList();
        if (!unavailableShowSeatIds.isEmpty()){
            throw new ShowSeatNotAvailableException("unavailable show seat ids : " + unavailableShowSeatIds);
        }

        // mark all showseats as BLOCKED for this user
        showSeats.forEach(showSeat -> {
            showSeat.setSeatStatus(SeatStatus.BLOCKED);
            showSeat.setBlockedAt(new Date());
        });
        showSeatRepository.saveAll(showSeats);

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
