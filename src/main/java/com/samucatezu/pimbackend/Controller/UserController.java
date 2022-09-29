package com.samucatezu.pimbackend.Controller;

import com.samucatezu.pimbackend.DTO.UserDto;
import com.samucatezu.pimbackend.DTO.UserInDto;
import com.samucatezu.pimbackend.Model.Role;
import com.samucatezu.pimbackend.Model.User;
import com.samucatezu.pimbackend.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @GetMapping
    public String get() {
        return "AWS";
    }
    @GetMapping("/users")
    public List<UserDto> getAllUsers(@RequestParam(required = false) Integer page) {
        return userService.getAllUsers(page);
    }

    @PostMapping("/users")
    public UserInDto saveUser(@RequestBody UserInDto userInDto) {
        return userService.saveUser(userInDto);
    }

    @PutMapping("/users/{id}")
    public User editUser(@PathVariable Long id, @RequestBody UserInDto userInDto) {
        return userService.editUser(id, userInDto);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/roles")
    public Role saveRole(@RequestBody Role role) {
        return userService.saveRole(role);
    }

    @PutMapping("/users/{id}/roles")
    public User addRoleToUser(@PathVariable Long id, @RequestParam String roleName) {
        return userService.addRoleToUser(id, roleName);
    }

    @DeleteMapping("/users/{id}/roles/{roleName}")
    public void deleteUserRole(@PathVariable Long id, @PathVariable String roleName) {
        userService.deleteUserRole(id, roleName);
    }

    @DeleteMapping("/users/insurances/{id}")
    public void deleteInsurance(@PathVariable Long id) {
        userService.deleteInsurance(id);
    }

}
