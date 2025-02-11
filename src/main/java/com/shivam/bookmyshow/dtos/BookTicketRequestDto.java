package com.shivam.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookTicketRequestDto {
    private long userId;
    private List<Long> showSeatIds;
}
