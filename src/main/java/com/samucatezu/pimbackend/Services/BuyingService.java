package com.samucatezu.pimbackend.Services;


import com.samucatezu.pimbackend.DTO.InsuranceDto;
import com.samucatezu.pimbackend.Details.CarCalculateDetails;
import com.samucatezu.pimbackend.Details.CarDetails;
import com.samucatezu.pimbackend.Details.HouseCalculateDetails;
import com.samucatezu.pimbackend.Details.HouseDetails;
import com.samucatezu.pimbackend.Model.Insurance;
import com.samucatezu.pimbackend.Model.User;
import com.samucatezu.pimbackend.Repository.InsuranceRepository;
import com.samucatezu.pimbackend.mapper.CalculateDetailsMapper;
import com.samucatezu.pimbackend.mapper.InsuranceDtoMapper;
import com.samucatezu.pimbackend.mapper.PrintableDetailsMapper;
import com.samucatezu.pimbackend.security.LoggedInUser;
import com.samucatezu.pimbackend.utils.Calculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.samucatezu.pimbackend.constant.InsuranceType.CAR;
import static com.samucatezu.pimbackend.constant.InsuranceType.HOUSE;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BuyingService {

    private final LoggedInUser loggedInUser;
    private final InsuranceRepository insuranceRepository;

    public InsuranceDto buyCarInsurance(CarDetails details) {
        CarCalculateDetails calculateDetails = CalculateDetailsMapper.mapToCarCalculateDetails(details);
        Integer price = Calculator.carInsuranceCalculator(calculateDetails);
        Integer time = details.getInsuranceTimeInYears();
        String printableDetails = PrintableDetailsMapper.mapToCarPrintableDetails(details);
        User user = loggedInUser.getUser();
        log.info("Registering a new car insurance");
        Insurance carInsurance = Insurance.builder()
                .type(CAR)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusYears(time))
                .price(price)
                .printableDetails(printableDetails)
                .user(user)
                .build();
        insuranceRepository.save(carInsurance);
        InsuranceDto insuranceDto = InsuranceDtoMapper.mapToInsuranceDisplayDto(carInsurance);
        return insuranceDto;
    }

    public InsuranceDto buyHouseInsurance(HouseDetails details) {
        HouseCalculateDetails calculateDetails = CalculateDetailsMapper.mapToHouseCalculateDetails(details);
        Integer price = Calculator.houseInsuranceCalculator(calculateDetails);
        Integer time = details.getInsuranceTimeInYears();
        String printableDetails = PrintableDetailsMapper.mapToHousePrintableDetails(details);
        User user = loggedInUser.getUser();
        log.info("Registering a new house insurance");
        Insurance houseInsurance = Insurance.builder()
                .type(HOUSE)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusYears(time))
                .price(price)
                .printableDetails(printableDetails)
                .user(user)
                .build();
        insuranceRepository.save(houseInsurance);
        InsuranceDto insuranceDto = InsuranceDtoMapper.mapToInsuranceDisplayDto(houseInsurance);
        return insuranceDto;
    }

}
