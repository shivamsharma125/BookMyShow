package com.shivam.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutResponseDto {
    private boolean loggedOut;
    private String failureMessage;
    private ResponseStatus responseStatus;
}
