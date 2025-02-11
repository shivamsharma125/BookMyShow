package com.shivam.bookmyshow.controllers;

import com.shivam.bookmyshow.dtos.BookTicketRequestDto;
import com.shivam.bookmyshow.dtos.BookTicketResponseDto;
import com.shivam.bookmyshow.dtos.ResponseStatus;
import com.shivam.bookmyshow.models.Booking;
import com.shivam.bookmyshow.services.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    public BookTicketResponseDto bookTicket(BookTicketRequestDto requestDto){
        BookTicketResponseDto responseDto = new BookTicketResponseDto();

        try {
            Booking booking = bookingService.bookTicket(
                    requestDto.getUserId(),
                    requestDto.getShowSeatIds()
            );
            responseDto.setBooking(booking);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception ex){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDto;
    }
}
