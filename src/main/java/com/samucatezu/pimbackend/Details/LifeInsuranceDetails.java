package com.samucatezu.pimbackend.Details;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LifeInsuranceDetails {

    private String rangeIncome;
    private String age;
    private String laborCamp;

}
