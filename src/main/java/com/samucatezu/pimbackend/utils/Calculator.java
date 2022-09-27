package com.samucatezu.pimbackend.utils;



import com.samucatezu.pimbackend.Details.CarCalculateDetails;
import com.samucatezu.pimbackend.Details.HouseCalculateDetails;
import com.samucatezu.pimbackend.Details.LifeCalculateDetails;

import java.time.LocalDate;

public class Calculator {

        private static int currentYear = LocalDate.now().getYear();

        private Calculator(){
        }

        public static Integer carInsuranceCalculator(CarCalculateDetails details) {

                if(details.getYearOfProduction() > currentYear || details.getYearOfProduction() < 1900)
                        throw new IllegalArgumentException("Incorrect Year Of Production!");

                if(details.getMileageInKm() < 0)
                        throw new IllegalArgumentException("Incorrect Mileage!");

                if(details.getEngineCapacityInCm3() > 8400 || details.getEngineCapacityInCm3() < 99)
                        throw new IllegalArgumentException("Incorrect Engine Capacity!");

                if(details.getInsuranceTimeInYears() < 1 || details.getInsuranceTimeInYears() > 10)
                        throw new IllegalArgumentException("Incorrect Insurance Time!");

                Integer yearCost = (currentYear - details.getYearOfProduction()) * 10;
                Integer mileageCost = details.getMileageInKm() / 100;
                Integer capacityCost = details.getEngineCapacityInCm3() / 2;
                Integer insuranceTime = details.getInsuranceTimeInYears();

                return (yearCost + mileageCost + capacityCost) * insuranceTime;

        }

        public static Integer houseInsuranceCalculator(HouseCalculateDetails details) {

                if(details.getConstructionYear() > currentYear || details.getConstructionYear() < 1800)
                        throw new IllegalArgumentException("Incorrect Year Of Construction!");

                if(details.getBuildingValue() < 50000 || details.getBuildingValue() > 10000000)
                        throw new IllegalArgumentException("Incorrect Building Value!");

                if(details.getInsuranceTimeInYears() < 1 || details.getInsuranceTimeInYears() > 10)
                        throw new IllegalArgumentException("Incorrect Insurance Time!");

                Integer yearCost = (currentYear - details.getConstructionYear()) * 25;
                Integer buildingValueCost = details.getBuildingValue() / 100;
                Integer insuranceTime = details.getInsuranceTimeInYears();

                return (yearCost + buildingValueCost) * insuranceTime;

        }

        public static Integer lifeInsuranceCalculator(LifeCalculateDetails details) {

                if(details.getYearOfBirth() > currentYear || details.getYearOfBirth() < 1900)
                        throw new IllegalArgumentException("Incorrect Year Of Birth!");

                if(details.getRangeIncome() < 1000)
                        throw new IllegalArgumentException("Incorrect Income!");

                if(details.getInsuranceTimeInYears() < 1 || details.getInsuranceTimeInYears() > 10)
                        throw new IllegalArgumentException("Incorrect Insurance Time!");

                Integer yearCost = (currentYear - details.getYearOfBirth()) * 5;
                Integer incomeCost = details.getRangeIncome() / 100;
                Integer insuranceTime = details.getInsuranceTimeInYears();

                return (yearCost + incomeCost) * insuranceTime;

        }

}
