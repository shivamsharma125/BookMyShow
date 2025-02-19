package com.shivam.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDto {
    private Long userId;
    private String failureMessage;
    private ResponseStatus responseStatus;
}
