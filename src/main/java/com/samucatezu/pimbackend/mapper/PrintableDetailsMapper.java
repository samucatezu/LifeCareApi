package com.samucatezu.pimbackend.mapper;

import com.samucatezu.pimbackend.Details.CarDetails;
import com.samucatezu.pimbackend.Details.HouseDetails;
import com.samucatezu.pimbackend.Details.LifeDetails;
import org.springframework.stereotype.Service;

@Service
public class PrintableDetailsMapper {

        public static String mapToCarPrintableDetails(CarDetails details) {

                return "\nBrand: " + details.getBrand()
                        + "\nModel: " + details.getModel()
                        + "\nRegistration Number: " + details.getRegistrationNumber()
                        + "\nYear Of Production: " + details.getYearOfProduction()
                        + "\nMileage (km): " + details.getMileageInKm()
                        + "\nEngine Capacity (cm3): " + details.getEngineCapacityInCm3();

        }

        public static String mapToHousePrintableDetails(HouseDetails details) {

                return "\nTown: " + details.getTown()
                        + "\nStreet: " + details.getStreet()
                        + "\nHouse Number: " + details.getHouseNumber()
                        + "\nZip Code: " + details.getZipCode()
                        + "\nConstruction Year: " + details.getConstructionYear()
                        + "\nBuilding Value: " + details.getBuildingValue();

        }

        public static String mapToLifePrintableDetails(LifeDetails details) {

                return "\nTown: " + details.getTown()
                        + "\nStreet: " + details.getStreet()
                        + "\nZip Code: " + details.getZipCode()
                        + "\nLabor camp: " + details.getLaborCamp()
                        + "\nRange income: " + details.getRangeIncome();

        }

}
