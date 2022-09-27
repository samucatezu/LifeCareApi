package com.samucatezu.pimbackend.Controller;


import com.samucatezu.pimbackend.DTO.InsuranceDto;
import com.samucatezu.pimbackend.Details.CarDetails;
import com.samucatezu.pimbackend.Details.HouseDetails;
import com.samucatezu.pimbackend.Services.BuyingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/buying")
public class BuyingController {

        private final BuyingService buyingService;

        @PostMapping("/carInsurances")
        public InsuranceDto buyCarInsurance(@RequestBody CarDetails details) {
                return buyingService.buyCarInsurance(details);
        }

        @PostMapping("/houseInsurances")
        public InsuranceDto buyHouseInsurance(@RequestBody HouseDetails details) {
                return buyingService.buyHouseInsurance(details);
        }

}
