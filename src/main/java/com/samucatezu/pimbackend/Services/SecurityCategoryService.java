package com.samucatezu.pimbackend.Services;

import com.samucatezu.pimbackend.Model.SecurityCategory;
import com.samucatezu.pimbackend.Repository.SecurityCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SecurityCategoryService {

    @Autowired
    private SecurityCategoryRepository securityCategoryRepository;

    public List<SecurityCategory> listCategories() {
        return securityCategoryRepository.findAll();
    }

    public void createCategory(SecurityCategory securityCategory) {
        securityCategoryRepository.save(securityCategory);
    }

    public SecurityCategory readCategory(String categoryName) {
        return securityCategoryRepository.findByCategoryName(categoryName);
    }

    public Optional<SecurityCategory> readCategory(Integer categoryId) {
        return securityCategoryRepository.findById(categoryId);
    }

    public void updateCategory(Integer categoryID, SecurityCategory newSecurityCategory) {
        SecurityCategory securityCategory = securityCategoryRepository.findById(categoryID).get();
        securityCategory.setCategoryName(newSecurityCategory.getCategoryName());
        securityCategory.setDescription(newSecurityCategory.getDescription());
        securityCategory.setImageUrl(newSecurityCategory.getImageUrl());
        securityCategoryRepository.save(securityCategory);
    }

}
