package com.samucatezu.pimbackend.Details;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class HouseCalculateDetails {

        private Integer constructionYear;
        private Integer buildingValue;
        private Integer insuranceTimeInYears;

}
