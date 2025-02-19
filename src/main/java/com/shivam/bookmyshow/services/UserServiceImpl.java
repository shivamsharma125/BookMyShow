package com.shivam.bookmyshow.services;

import com.shivam.bookmyshow.exceptions.UserAlreadyExists;
import com.shivam.bookmyshow.exceptions.UserAlreadyLoggedOut;
import com.shivam.bookmyshow.exceptions.UserNotFoundException;
import com.shivam.bookmyshow.models.User;
import com.shivam.bookmyshow.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User signUp(String userName, String email, String password) throws UserAlreadyExists {
        // check user already exists or not
        boolean exists = userRepository.existsByEmail(email);
        if (exists) {
            throw new UserAlreadyExists("user with this email already exists");
        }

        // create user and return
        User user = new User();
        user.setName(userName);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) throws UserNotFoundException {
        return null;
    }

    @Override
    public boolean logout(long userId) throws UserAlreadyLoggedOut {
        return false;
    }
}
