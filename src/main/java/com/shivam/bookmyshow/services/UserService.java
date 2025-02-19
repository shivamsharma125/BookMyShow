package com.shivam.bookmyshow.services;

import com.shivam.bookmyshow.exceptions.UserAlreadyExists;
import com.shivam.bookmyshow.exceptions.UserAlreadyLoggedOut;
import com.shivam.bookmyshow.exceptions.UserNotFoundException;
import com.shivam.bookmyshow.models.User;

public interface UserService {
    User signUp(String userName, String email, String password) throws UserAlreadyExists;
    User login(String email, String password) throws UserNotFoundException;
    boolean logout(long userId) throws UserAlreadyLoggedOut;
}
