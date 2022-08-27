package com.samucatezu.pimbackend.Services;


import com.samucatezu.pimbackend.Model.AppUserDetails;
import com.samucatezu.pimbackend.Repository.AppUserDetailsRepository;
import com.samucatezu.pimbackend.registration.Confirmation.ConfirmationToken;
import com.samucatezu.pimbackend.registration.Confirmation.ConfirmationTokenService;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
@AllArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final AppUserDetailsRepository appUserDetailsRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserDetailsRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUserDetails AppUserDetails) {
        boolean userExists = appUserDetailsRepository
                .findByEmail(AppUserDetails.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(AppUserDetails.getPassword());

        AppUserDetails.setPassword(encodedPassword);

        appUserDetailsRepository.save(AppUserDetails);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                AppUserDetails
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }

    public int enableAppUser(String email) {
        return appUserDetailsRepository.enableAppUser(email);
    }
}