package com.samucatezu.pimbackend.Controller;

import com.samucatezu.pimbackend.DTO.UserInDto;
import com.samucatezu.pimbackend.Services.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/registration")
    public String registerUser(@RequestBody UserInDto userInDto) {
        return registrationService.registerUser(userInDto);
    }

    @GetMapping("/registration/confirm")
    public String confirm(@RequestParam String token) {
        return registrationService.confirmToken(token);
    }

}

