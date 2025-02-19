package com.shivam.bookmyshow.utils;

import com.shivam.bookmyshow.exceptions.InvalidShowSeatException;
import com.shivam.bookmyshow.exceptions.ShowSeatNotAvailableException;
import com.shivam.bookmyshow.models.SeatStatus;
import com.shivam.bookmyshow.models.ShowSeat;
import com.shivam.bookmyshow.repositories.ShowSeatRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ShowSeatUtils {

    private ShowSeatRepository showSeatRepository;

    public ShowSeatUtils(ShowSeatRepository showSeatRepository) {
        this.showSeatRepository = showSeatRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<ShowSeat> checkAndBlockedShowSeats(List<Long> showSeatIds) throws InvalidShowSeatException, ShowSeatNotAvailableException {
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
        });
        showSeatRepository.saveAll(showSeats);
        return showSeats;
    }
}
