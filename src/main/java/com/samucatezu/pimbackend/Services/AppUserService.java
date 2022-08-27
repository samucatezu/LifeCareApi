package com.samucatezu.pimbackend.Services;

import com.samucatezu.pimbackend.Model.AppUser;
import com.samucatezu.pimbackend.Repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
@AllArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;

    public List<AppUser> listAppUser() { return appUserRepository.findAll(); }

    public void createAppUser(AppUser appUser) {
        appUserRepository.save(appUser); }

    public AppUser readAppUser(Long appUserId) {
        return appUserRepository.findById(appUserId).orElseThrow(EntityNotFoundException::new);
    }

    public void updateAppUser(Long appUserDetailsId, AppUser newUserDetails) {
        AppUser appUser = readAppUser(appUserDetailsId);
        appUser.setCpf(newUserDetails.getCpf());
        appUser.setUf(newUserDetails.getUf());
        appUser.setCity(newUserDetails.getCity());
        appUser.setStreetName(newUserDetails.getStreetName());
        appUser.setStreetNumber(newUserDetails.getStreetNumber());
        appUser.setZipCode(newUserDetails.getZipCode());
        appUserRepository.save(appUser);
    }
}
