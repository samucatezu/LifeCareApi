package com.samucatezu.pimbackend.Services;

import com.samucatezu.pimbackend.Model.Category;
import com.samucatezu.pimbackend.Repository.InsuranceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private InsuranceCategoryRepository insuranceCategoryRepository;

    public List<Category> listInsuranceCategories() {
        return insuranceCategoryRepository.findAll();
    }

    public void createInsuranceCategory(Category category) {
        insuranceCategoryRepository.save(category);
    }

    public Category readCategory(String categoryName) {
        return insuranceCategoryRepository.findByCategoryName(categoryName);
    }

    public Optional<Category> readCategory(Integer categoryId) {
        return insuranceCategoryRepository.findById(categoryId);
    }

    public void updateInsuranceCategory(Integer categoryID, Category newCategory) {
        Category category = insuranceCategoryRepository.findById(categoryID).get();
        category.setCategoryName(newCategory.getCategoryName());
        category.setDescription(newCategory.getDescription());
        category.setImageUrl(newCategory.getImageUrl());
        insuranceCategoryRepository.save(category);
    }

}
