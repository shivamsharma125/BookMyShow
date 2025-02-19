package com.shivam.bookmyshow.controllers;

import com.shivam.bookmyshow.dtos.*;
import com.shivam.bookmyshow.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    SignUpResponseDto signUp(SignUpRequestDto requestDto) {
        return null;
    }

    LoginResponseDto login(LoginRequestDto requestDto) {
        return null;
    }

    LogoutResponseDto logout(LogoutRequestDto requestDto) {
        return null;
    }
}
