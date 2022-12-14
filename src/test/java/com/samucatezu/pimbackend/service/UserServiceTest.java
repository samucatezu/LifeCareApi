package com.samucatezu.pimbackend.service;


import com.samucatezu.pimbackend.DTO.UserInDto;
import com.samucatezu.pimbackend.Model.Role;
import com.samucatezu.pimbackend.Model.User;
import com.samucatezu.pimbackend.Repository.RoleRepository;
import com.samucatezu.pimbackend.Repository.UserRepository;
import com.samucatezu.pimbackend.Services.UserService;
import com.samucatezu.pimbackend.exception.AssignedRoleException;
import com.samucatezu.pimbackend.exception.ExistingEntityException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

        @Mock
        private UserRepository userRepository;

        @Mock
        RoleRepository roleRepository;

        @Mock
        PasswordEncoder passwordEncoder;

        @InjectMocks
        private UserService userService;

        @Test
        void itShouldReturnAllUsers() {

                User user1 = User.builder()
                        .username("Samucatezu")
                        .build();

                User user2 = User.builder()
                        .username("ErikFazMolho")
                        .build();

                User user3 = User.builder()
                        .username("AleArrumaEstagio")
                        .build();

                User user4 = User.builder()
                        .username("NetaoHamburguer")
                        .build();

                List<User> userList = Arrays.asList(user1, user2, user3, user4);


                given(userRepository.findUsers(PageRequest.of(0,5))).willReturn(userList);


                assertThat(userService.getAllUsers(1)).hasSize(4);

        }

        @Test
        void itShouldSaveNewUser() {

                UserInDto userInDto = UserInDto.builder()
                        .username("Burgao")
                        .build();

                User user = User.builder()
                        .username("Burgao")
                        .insurances(new ArrayList<>())
                        .roles(new ArrayList<>())
                        .build();


                given(userRepository.findByUsername("Burgao")).willReturn(Optional.empty());
                given(userRepository.save(user)).willReturn(user);


                UserInDto saved = userService.saveUser(userInDto);

                assertThat(saved.getUsername()).isEqualTo(user.getUsername());

        }

        @Test
        void itShouldEditUser() {

                UserInDto userInDto = UserInDto.builder()
                        .username("George98")
                        .build();

                User user = User.builder()
                        .id(2L)
                        .build();


                given(userRepository.findById(2L)).willReturn(Optional.of(user));
                given(userRepository.save(user)).willReturn(user);


                assertThat(userService.editUser(2L, userInDto).getUsername()).isEqualTo(user.getUsername());

        }

        @Test
        void itShouldDeleteUser() {

                User user = User.builder()
                        .id(43L)
                        .build();


                given(userRepository.existsById(43L)).willReturn(true);
                doNothing().when(userRepository).deleteById(43L);


                userService.deleteUser(43L);

                verify(userRepository, times(1)).deleteById(43L);

        }

        @Test
        void itShouldSaveRole() {

                Role role = Role.builder()
                        .name("ROLE_KING")
                        .build();


                given(roleRepository.findByName("ROLE_KING")).willReturn(Optional.empty());
                given(roleRepository.save(role)).willReturn(role);


                Role saved = userService.saveRole(role);

                assertThat(saved).isEqualTo(role);

        }

        @Test
        void itShouldAddRoleToUser() {

                User user = User.builder()
                        .id(3L)
                        .username("Samucatezu")
                        .roles(new ArrayList<>())
                        .build();

                Role role = Role.builder()
                        .name("ROLE_ADMIN")
                        .users(new ArrayList<>())
                        .build();


                given(userRepository.findById(3L)).willReturn(Optional.of(user));
                given(roleRepository.findByName("ROLE_ADMIN")).willReturn(Optional.of(role));
                given(userRepository.save(user)).willReturn(user);


                userService.addRoleToUser(3L, "ROLE_ADMIN");

                assertThat(user.getRoles().contains(role));
                assertThat(role.getUsers().contains(user));

        }

        @Test
        void itShouldDeleteUserRole() {

                User user = User.builder()
                        .id(6L)
                        .username("ENZO")
                        .roles(new ArrayList<>())
                        .build();

                Role role = Role.builder()
                        .name("ROLE_USER")
                        .users(new ArrayList<>())
                        .build();


                given(userRepository.findById(6L)).willReturn(Optional.of(user));
                given(roleRepository.findByName("ROLE_USER")).willReturn(Optional.of(role));
                given(userRepository.save(user)).willReturn(user);


                userService.addRoleToUser(6L, "ROLE_USER");

                userService.deleteUserRole(6L, "ROLE_USER");

                assertThat(user.getRoles()).hasSize(0);

        }

        @Test
        void itShouldThrowExistingUserException() {

                UserInDto userInDto = UserInDto.builder()
                        .username("NetaoQueroPizza")
                        .build();

                User user = User.builder()
                        .username("NetaoQueroPizza")
                        .build();


                given(userRepository.findByUsername("NetaoQueroPizza")).willReturn(Optional.of(user));


                assertThrows(ExistingEntityException.class, () -> userService.saveUser(userInDto));

        }

        @Test
        void itShouldThrowExistingRoleException() {

                Role role = Role.builder()
                        .name("ROLE_USER")
                        .build();

                Role role2 = Role.builder()
                        .name("ROLE_USER")
                        .build();


                given(roleRepository.findByName("ROLE_USER")).willReturn(Optional.of(role2));


                assertThrows(ExistingEntityException.class, () -> userService.saveRole(role));

        }

        @Test
        void itShouldThrowAssignedRoleException() {

                Role role = Role.builder()
                        .name("ROLE_USER")
                        .build();

                User user = User.builder()
                        .id(7L)
                        .roles(Arrays.asList(role))
                        .build();


                given(userRepository.findById(7L)).willReturn(Optional.of(user));
                given(roleRepository.findByName("ROLE_USER")).willReturn(Optional.of(role));


                assertThrows(AssignedRoleException.class, () -> userService.addRoleToUser(7L, "ROLE_USER"));

        }

}
