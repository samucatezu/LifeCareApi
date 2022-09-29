package com.samucatezu.pimbackend.Details;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LifeCalculateDetails {

    private Integer rangeIncome;
    private Integer yearOfBirth;
    private Integer insuranceTimeInYears;
    private String laborCamp;

}
