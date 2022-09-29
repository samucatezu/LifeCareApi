package com.samucatezu.pimbackend.Controller;

import com.samucatezu.pimbackend.Services.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/policies")
@CrossOrigin
public class PolicyController {

        private final PolicyService policyService;

        @GetMapping("/generate")
        public void createPolicy(HttpServletResponse response, @RequestParam Long insuranceId) {
                policyService.createPolicy(response, insuranceId);
        }

}
