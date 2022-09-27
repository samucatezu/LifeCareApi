package com.samucatezu.pimbackend.Controller;


import com.samucatezu.pimbackend.Details.CarCalculateDetails;
import com.samucatezu.pimbackend.Details.HouseCalculateDetails;
import com.samucatezu.pimbackend.Details.InsurancePrice;
import com.samucatezu.pimbackend.Services.CalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calculation")
public class CalculationController {

        private final CalculationService calculationService;

        @GetMapping("/cars")
        public InsurancePrice carInsuranceCalculation(@RequestBody CarCalculateDetails details) {
                return calculationService.carInsuranceCalculation(details);
        }

        @GetMapping("/houses")
        public InsurancePrice houseInsuranceCalculation(@RequestBody HouseCalculateDetails details) {
                return calculationService.houseInsuranceCalculation(details);
        }

}
