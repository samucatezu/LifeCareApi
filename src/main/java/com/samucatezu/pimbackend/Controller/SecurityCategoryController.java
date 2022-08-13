package com.samucatezu.pimbackend.Controller;

import com.samucatezu.pimbackend.Common.ApiResponse;
import com.samucatezu.pimbackend.Model.SecurityCategory;
import com.samucatezu.pimbackend.Services.SecurityCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/SecurityCategory")
public class SecurityCategoryController {
    @Autowired
    private SecurityCategoryService securitycategoryService;

    @GetMapping("/")
    public ResponseEntity<List<SecurityCategory>> getCategories() {
        List<SecurityCategory> body = securitycategoryService.listCategories();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody SecurityCategory securityCategory) {
        if (Objects.nonNull(securitycategoryService.readCategory(securityCategory.getCategoryName()))) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
        }
        securitycategoryService.createCategory(securityCategory);
        return new ResponseEntity<>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
    }


    @PostMapping("/update/{categoryID}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryID") Integer categoryID, @Valid @RequestBody SecurityCategory securityCategory) {
        // Check to see if the category exists.
        if (Objects.nonNull(securitycategoryService.readCategory(categoryID))) {
            // If the category exists then update it.
            securitycategoryService.updateCategory(categoryID, securityCategory);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, "updated the category"), HttpStatus.OK);
        }

        // If the category doesn't exist then return a response of unsuccessful.
        return new ResponseEntity<>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
    }
}
