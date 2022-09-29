package com.samucatezu.pimbackend.mapper;

import com.samucatezu.pimbackend.DTO.UserInDto;
import com.samucatezu.pimbackend.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserInDtoMapper {

    public static User mapToUser(UserInDto userInDto) {

        return User.builder()
                .firstName(userInDto.getFirstName())
                .lastName(userInDto.getLastName())
                .username(userInDto.getUsername())
                .dayOfBirth(userInDto.getDayOfBirth())
                .monthOfBirth(userInDto.getMonthOfBirth())
                .yearOfBirth(userInDto.getYearOfBirth())
                .email(userInDto.getEmail())
                .phone(userInDto.getPhone())
                .password(userInDto.getPassword())
                .roles(new ArrayList<>())
                .insurances(new ArrayList<>())
                .build();

    }

}
