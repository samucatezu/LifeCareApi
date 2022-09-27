package com.samucatezu.pimbackend.Services;

import com.samucatezu.pimbackend.DTO.UserInDto;
import com.samucatezu.pimbackend.Model.ConfirmationToken;
import com.samucatezu.pimbackend.Model.User;
import com.samucatezu.pimbackend.Repository.UserRepository;
import com.samucatezu.pimbackend.exception.ExistingEntityException;
import com.samucatezu.pimbackend.exception.WeakPasswordException;
import com.samucatezu.pimbackend.mapper.UserInDtoMapper;
import com.samucatezu.pimbackend.utils.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class RegistrationService {

        private final CustomUserDetailsService customUserDetailsService;
        private final ConfirmationTokenService confirmationTokenService;
        private final UserRepository userRepository;

        public String registerUser(UserInDto userInDto) {

                if(userRepository.findByEmail(userInDto.getEmail()).isPresent()) {

                        throw new ExistingEntityException("User With Given Email Already Exists!");
                } else if(userRepository.findByUsername(userInDto.getUsername()).isPresent()) {

                        throw new ExistingEntityException("User With Given Username Already Exists!");
                } else if(!PasswordValidator.matcher(userInDto.getPassword()).matches()) {

                        throw new WeakPasswordException("Password Must Contains At Least 8 Characters"
                        + ", At Least One Lowercase Letter, At Least One Uppercase Letter, At Least One Digit"
                        + " And At Least One Special Character");
                } else {
                        User mappedUser = UserInDtoMapper.mapToUser(userInDto);
                        return customUserDetailsService.signUpUser(mappedUser);
                }

        }

        public String confirmToken(String token) {
                ConfirmationToken confirmationToken = confirmationTokenService
                        .getToken(token);
                if (confirmationToken.getConfirmedAt() != null) {

                        throw new IllegalStateException("Email Already Confirmed!");
                }
                LocalDateTime expiredAt = confirmationToken.getExpiresAt();
                if (expiredAt.isBefore(LocalDateTime.now())) {

                        throw new IllegalStateException("Token Expired!");
                }
                confirmationTokenService.setConfirmedAt(token);
                customUserDetailsService.enableUser(confirmationToken.getUser().getEmail());
                return "Confirmed";
        }

}
