package com.shivam.bookmyshow.dtos;

import com.shivam.bookmyshow.models.Booking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookTicketResponseDto {
    private Booking booking;
    private ResponseStatus responseStatus;
}
