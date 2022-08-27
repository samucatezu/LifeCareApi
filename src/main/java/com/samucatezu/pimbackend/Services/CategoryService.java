package com.samucatezu.pimbackend.Services;

import com.samucatezu.pimbackend.Model.Category;
import com.samucatezu.pimbackend.Repository.InsuranceCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private final InsuranceCategoryRepository insuranceCategoryRepository;

    public List<Category> listInsuranceCategories() {
        return insuranceCategoryRepository.findAll();
    }

    public Category createInsuranceCategory(Category category) {
        return insuranceCategoryRepository.save(category);
    }

    public Category readCategory(String categoryName) {
        return insuranceCategoryRepository.findByCategoryName(categoryName);
    }

    public Category readCategory(Long categoryId) {
        return insuranceCategoryRepository.findById(categoryId).orElseThrow(EntityNotFoundException::new);
    }

    public void updateInsuranceCategory(Long categoryID, Category newCategory) {
        Category category = readCategory(categoryID);
        category.setCategoryName(newCategory.getCategoryName());
        category.setDescription(newCategory.getDescription());
        category.setImageUrl(newCategory.getImageUrl());
        insuranceCategoryRepository.save(category);
    }

}
