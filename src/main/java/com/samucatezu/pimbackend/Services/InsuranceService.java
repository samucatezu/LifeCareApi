package com.samucatezu.pimbackend.Services;


import com.samucatezu.pimbackend.DTO.InsuranceDTO;
import com.samucatezu.pimbackend.Model.Insurance;
import com.samucatezu.pimbackend.Model.Category;
import com.samucatezu.pimbackend.Repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InsuranceService {

    @Autowired
    private InsuranceRepository insuranceRepository;

    public static Insurance getInsuranceFromDto(InsuranceDTO insuranceDTO, Category category) {
        Insurance insurance = new Insurance();
        insurance.setCategory(category);
        insurance.setDescription(insuranceDTO.getDescription());
        insurance.setImageURL(insuranceDTO.getImageURL());
        insurance.setPrice(insuranceDTO.getPrice());
        insurance.setName(insuranceDTO.getName());
        return insurance;
    }

    public void addInsurance(InsuranceDTO insuranceDTO, Category category) {
        Insurance insurance = getInsuranceFromDto(insuranceDTO, category);
        insuranceRepository.save(insurance);
    }

    // list of all the products
    public List<InsuranceDTO> listInsurances() {
        // first fetch all the products
        List<Insurance> insurances = insuranceRepository.findAll();
        List<InsuranceDTO> insuranceDTOS = new ArrayList<>();

        for(Insurance insurance : insurances) {
            // for each product change it to DTO
            insuranceDTOS.add(new InsuranceDTO(insurance));
        }
        return insuranceDTOS;
    }

    // update a product
    public void updateInsurance(Integer insuranceID, InsuranceDTO insuranceDTO, Category category) {
        Insurance insurance = getInsuranceFromDto(insuranceDTO, category);
        // set the id for updating
        insurance.setId(insuranceID);
        // update
        insuranceRepository.save(insurance);
    }

}
