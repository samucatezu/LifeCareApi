package com.samucatezu.pimbackend.DTO;

import com.samucatezu.pimbackend.Model.Insurance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceDTO {

    private Long id;
    private @NotNull String name;
    private @NotNull String imageURL;
    private @NotNull double price;
    private @NotNull String description;
    private @NotNull Long categoryId;


    public InsuranceDTO(Insurance insurance) {
        this.setId(insurance.getId());
        this.setName(insurance.getName());
        this.setImageURL(insurance.getImageURL());
        this.setDescription(insurance.getDescription());
        this.setPrice(insurance.getPrice());
        this.setCategoryId(insurance.getCategory().getId());
    }



}
