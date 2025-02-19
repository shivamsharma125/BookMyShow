package com.shivam.bookmyshow.controllers;

import com.shivam.bookmyshow.dtos.*;
import com.shivam.bookmyshow.models.User;
import com.shivam.bookmyshow.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {
        SignUpResponseDto responseDto = new SignUpResponseDto();

        try {
            User user = userService.signUp(
                    requestDto.getUsername(),
                    requestDto.getEmail(),
                    requestDto.getPassword()
            );
            responseDto.setUserId(user.getId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            responseDto.setFailureMessage(e.getMessage());
        }

        return responseDto;
    }

    public LoginResponseDto login(LoginRequestDto requestDto) {
        return null;
    }

    public LogoutResponseDto logout(LogoutRequestDto requestDto) {
        return null;
    }
}
