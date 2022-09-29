package com.samucatezu.pimbackend.mapper;


import com.samucatezu.pimbackend.Details.*;
import org.springframework.stereotype.Service;

@Service
public class CalculateDetailsMapper {

        public static CarCalculateDetails mapToCarCalculateDetails(CarDetails details) {

                return CarCalculateDetails.builder()
                        .yearOfProduction(details.getYearOfProduction())
                        .mileageInKm(details.getMileageInKm())
                        .engineCapacityInCm3(details.getEngineCapacityInCm3())
                        .insuranceTimeInYears(details.getInsuranceTimeInYears())
                        .build();

        }

        public static HouseCalculateDetails mapToHouseCalculateDetails(HouseDetails details) {

                return HouseCalculateDetails.builder()
                        .constructionYear(details.getConstructionYear())
                        .buildingValue(details.getBuildingValue())
                        .insuranceTimeInYears(details.getInsuranceTimeInYears())
                        .build();

        }

        public static LifeCalculateDetails mapToLifeCalculateDetails(LifeDetails details) {
                return  LifeCalculateDetails.builder()
                        .yearOfBirth(details.getYearOfBirth())
                        .insuranceTimeInYears(details.getInsuranceTimeInYears())
                        .rangeIncome(details.getRangeIncome())
                        .laborCamp(details.getLaborCamp())
                        .build();
        }

}
