package com.samucatezu.pimbackend.service;


import com.samucatezu.pimbackend.DTO.InsuranceDto;
import com.samucatezu.pimbackend.Details.CarDetails;
import com.samucatezu.pimbackend.Details.HouseDetails;
import com.samucatezu.pimbackend.Details.LifeCalculateDetails;
import com.samucatezu.pimbackend.Details.LifeDetails;
import com.samucatezu.pimbackend.Model.Insurance;
import com.samucatezu.pimbackend.Model.User;
import com.samucatezu.pimbackend.Repository.InsuranceRepository;
import com.samucatezu.pimbackend.Services.BuyingService;
import com.samucatezu.pimbackend.constant.InsuranceType;
import com.samucatezu.pimbackend.security.LoggedInUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BuyingServiceTest {

        @Mock
        private InsuranceRepository insuranceRepository;

        @Mock
        private LoggedInUser loggedInUser;

        @InjectMocks
        private BuyingService buyingService;


        @Test
        void itShouldBuyCarInsurance() {

                CarDetails carDetails = CarDetails.builder()
                        .brand("Peugeot")
                        .model("208")
                        .registrationNumber("ERD-76409")
                        .yearOfProduction(2019)
                        .mileageInKm(8500)
                        .engineCapacityInCm3(1800)
                        .insuranceTimeInYears(1)
                        .build();

                User user = User.builder()
                        .build();

                Insurance carInsurance = Insurance.builder()
                        .type(InsuranceType.CAR)
                        .startDate(LocalDate.now())
                        .endDate(LocalDate.now().plusYears(1))
                        .price(1015)
                        .printableDetails("\nBrand: Peugeot\nModel: 208\nRegistration Number: ERD-76409"
                                + "\nYear Of Production: 2019\nMileage (km): 8500\nEngine Capacity (cm3): 1800")
                        .user(user)
                        .build();

                InsuranceDto insuranceDto = InsuranceDto.builder()
                        .type(InsuranceType.CAR)
                        .startDate(LocalDate.now())
                        .endDate(LocalDate.now().plusYears(1))
                        .price(1015)
                        .build();


                given(insuranceRepository.save(carInsurance)).willReturn(carInsurance);
                given(loggedInUser.getUser()).willReturn(user);


                assertThat(buyingService.buyCarInsurance(carDetails)).isEqualTo(insuranceDto);

        }

        @Test
        void itShouldBuyHouseInsurance() {

                HouseDetails houseDetails = HouseDetails.builder()
                        .town("Guaruja")
                        .street("Leomil")
                        .houseNumber("238")
                        .zipCode("01311-000")
                        .constructionYear(1978)
                        .buildingValue(410000)
                        .insuranceTimeInYears(6)
                        .build();

                User user = User.builder()
                        .insurances(new ArrayList<>())
                        .build();

                Insurance houseInsurance = Insurance.builder()
                        .type(InsuranceType.HOUSE)
                        .startDate(LocalDate.now())
                        .endDate(LocalDate.now().plusYears(6))
                        .price(31200)
                        .printableDetails("\nTown: Guaruja\nStreet: Leomil\nHouse Number: 238"
                                + "\nZip Code: 01311-000\nConstruction Year: 1978\nBuilding Value: 410000")
                        .user(user)
                        .build();

                InsuranceDto insuranceDto = InsuranceDto.builder()
                        .type(InsuranceType.HOUSE)
                        .startDate(LocalDate.now())
                        .endDate(LocalDate.now().plusYears(6))
                        .price(31200)
                        .build();


                given(insuranceRepository.save(houseInsurance)).willReturn(houseInsurance);
                given(loggedInUser.getUser()).willReturn(user);


                assertThat(buyingService.buyHouseInsurance(houseDetails)).isEqualTo(insuranceDto);

        }

        @Test
        void itShouldBuyLifeInsurance() {

                LifeDetails lifeDetails = LifeDetails.builder()
                        .town("Santos")
                        .street("Av Rangel Pestana")
                        .zipCode("01311-000")
                        .rangeIncome(5000)
                        .yearOfBirth(2000)
                        .insuranceTimeInYears(2)
                        .laborCamp("Developer")
                        .build();

                User user = User.builder()
                        .insurances(new ArrayList<>())
                        .build();

                Insurance lifeInsurance = Insurance.builder()
                        .type(InsuranceType.LIFE)
                        .startDate(LocalDate.now())
                        .endDate(LocalDate.now().plusYears(2))
                        .price(31200)
                        .printableDetails("\nTown: Santos\nStreet: Av Rangel Pestana\n Zip Code: 01311-000"
                                + "\n Labor camp: Developer\nRange Incomer: 5000\nYear of Birth: 2000")
                        .user(user)
                        .build();

                InsuranceDto insuranceDto = InsuranceDto.builder()
                        .type(InsuranceType.LIFE)
                        .startDate(LocalDate.now())
                        .endDate(LocalDate.now().plusYears(2))
                        .price(4000)
                        .build();


                given(insuranceRepository.save(lifeInsurance)).willReturn(lifeInsurance);
                given(loggedInUser.getUser()).willReturn(user);


                assertThat(buyingService.buyLifeInsurance(lifeDetails)).isEqualTo(insuranceDto);

        }

}