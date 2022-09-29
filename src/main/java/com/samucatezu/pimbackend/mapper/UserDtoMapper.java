package com.samucatezu.pimbackend.mapper;

import com.samucatezu.pimbackend.DTO.UserDto;
import com.samucatezu.pimbackend.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDtoMapper {

    public static List<UserDto> mapToUserDto(List<User> users) {

        return users.stream()
                .map(user -> new UserDto(user.getId(), user.getFirstName(),
                        user.getLastName(), user.getUsername(), user.getDayOfBirth(),
                        user.getMonthOfBirth(), user.getYearOfBirth(), user.getEmail(),
                        user.getPhone(), user.getPassword())).collect(Collectors.toList());

    }

}