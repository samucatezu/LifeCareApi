package com.samucatezu.pimbackend.Controller;

import com.samucatezu.pimbackend.Common.ApiResponse;
import com.samucatezu.pimbackend.Model.AppUser;

import com.samucatezu.pimbackend.Services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("UserForm")
public class AppUserController {

    @Autowired
    AppUserService appUserService;

    @GetMapping("/all-users")
    public ResponseEntity<List<AppUser>> GetAppUser() {
        List<AppUser> body = appUserService.listAppUser();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createAppUser(@Valid @RequestBody AppUser appUser) {
        if (Objects.nonNull(appUserService.readAppUser(appUser.getId()))) {
            return new ResponseEntity<>(new ApiResponse(false, "User already exists"), HttpStatus.CONFLICT);
        }
        appUserService.createAppUser(appUser);
        return new ResponseEntity<>(new ApiResponse(true, "created the user"), HttpStatus.CREATED);
    }

}
