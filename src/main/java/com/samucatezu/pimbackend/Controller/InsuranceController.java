package com.samucatezu.pimbackend.Controller;

import com.samucatezu.pimbackend.Common.ApiResponse;
import com.samucatezu.pimbackend.DTO.InsuranceDTO;
import com.samucatezu.pimbackend.Model.Category;
import com.samucatezu.pimbackend.Services.CategoryService;
import com.samucatezu.pimbackend.Services.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    InsuranceService insuranceService;
    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addInsurance(@RequestBody InsuranceDTO insuranceDTO) {
       try {
           Category insuranceCategory = categoryService.readCategory(insuranceDTO.getCategoryId());
           insuranceService.addInsurance(insuranceDTO, insuranceCategory);
           return new ResponseEntity<>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
       } catch(EntityNotFoundException e){ return new ResponseEntity<>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);}
    }

    // update a product
    @PostMapping("/update/{insuranceID}")
    public ResponseEntity<ApiResponse> updateInsurance(@PathVariable("insuranceID") Long insuranceID, @RequestBody @Valid InsuranceDTO insuranceDTO) {
        try {
        Category insuranceCategory = categoryService.readCategory(insuranceDTO.getCategoryId());
        insuranceService.updateInsurance(insuranceID, insuranceDTO, insuranceCategory);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
        } catch(EntityNotFoundException e){ return new ResponseEntity<>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);}
    }

    // list all the products
    @GetMapping("/listall")
    public ResponseEntity<List<InsuranceDTO>> getInsurances() {
        List<InsuranceDTO> insuranceDTOS = insuranceService.listInsurances();
        return new ResponseEntity<>(insuranceDTOS, HttpStatus.OK);
    }
}
