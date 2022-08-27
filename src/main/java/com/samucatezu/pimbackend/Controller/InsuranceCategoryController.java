package com.samucatezu.pimbackend.Controller;

import com.samucatezu.pimbackend.Common.ApiResponse;
import com.samucatezu.pimbackend.Model.Category;
import com.samucatezu.pimbackend.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/SecurityCategory")
public class InsuranceCategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> body = categoryService.listInsuranceCategories();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category) {
        if (Objects.nonNull(categoryService.readCategory(category.getCategoryName()))) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
        }
        categoryService.createInsuranceCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
    }


    @PatchMapping("/update/{categoryID}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryID") Long categoryID, @Valid @RequestBody Category category) {
        // Check to see if the category exists.
        if (Objects.nonNull(categoryService.readCategory(categoryID))) {
            // If the category exists then update it.
            categoryService.updateInsuranceCategory(categoryID, category);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, "updated the category"), HttpStatus.OK);
        }

        // If the category doesn't exist then return a response of unsuccessful.
        return new ResponseEntity<>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
    }
}
