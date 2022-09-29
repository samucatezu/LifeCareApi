package com.samucatezu.pimbackend.mapper;

import com.samucatezu.pimbackend.DTO.InsuranceDto;
import com.samucatezu.pimbackend.Model.Insurance;
import org.springframework.stereotype.Service;

@Service
public class InsuranceDtoMapper {

        public static InsuranceDto mapToInsuranceDisplayDto(Insurance insurance) {

                return InsuranceDto.builder()
                        .id(insurance.getId())
                        .type(insurance.getType())
                        .startDate(insurance.getStartDate())
                        .endDate(insurance.getEndDate())
                        .price(insurance.getPrice())
                        .build();

        }

}
