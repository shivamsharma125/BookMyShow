package com.shivam.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private UserDto user;
    private String failureMessage;
    private ResponseStatus responseStatus;
}
