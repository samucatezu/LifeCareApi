package com.samucatezu.pimbackend.security;

import com.samucatezu.pimbackend.Model.User;
import com.samucatezu.pimbackend.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggedInUser {

    private final UserRepository userRepository;

    public User getUser() {

        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        String username = principal.getName();
        return userRepository.findByUsername(username).orElseThrow();

    }

}
