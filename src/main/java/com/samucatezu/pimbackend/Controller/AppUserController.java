package com.samucatezu.pimbackend.Controller;

import com.samucatezu.pimbackend.Common.ApiResponse;
import com.samucatezu.pimbackend.Model.AppUser;

import com.samucatezu.pimbackend.Model.Category;
import com.samucatezu.pimbackend.Repository.AppUserRepository;
import com.samucatezu.pimbackend.Services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("UserForm")
public class AppUserController {

    @Autowired
    AppUserService appUserService;

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/all-users")
    public ResponseEntity<List<AppUser>> GetAppUser() {
        List<AppUser> body = appUserService.listAppUser();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AppUser> createAppUser( @RequestBody AppUser appUser) {
        try {
            AppUser _appuser = appUserRepository.save(new AppUser());
            return new ResponseEntity<>(_appuser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
