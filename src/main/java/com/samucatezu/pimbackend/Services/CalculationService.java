package com.samucatezu.pimbackend.Services;

import com.samucatezu.pimbackend.Details.CarCalculateDetails;
import com.samucatezu.pimbackend.Details.HouseCalculateDetails;
import com.samucatezu.pimbackend.Details.InsurancePrice;
import com.samucatezu.pimbackend.utils.Calculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class CalculationService {

    public InsurancePrice carInsuranceCalculation(CarCalculateDetails details) {
        log.info("Calculation performed");
        return new InsurancePrice(Calculator.carInsuranceCalculator(details));
    }

    public InsurancePrice houseInsuranceCalculation(HouseCalculateDetails details) {
        log.info("Calculation performed");
        return new InsurancePrice(Calculator.houseInsuranceCalculator(details));
    }

}
