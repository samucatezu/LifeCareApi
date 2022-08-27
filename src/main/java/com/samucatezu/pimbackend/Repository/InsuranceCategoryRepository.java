package com.samucatezu.pimbackend.Repository;


import com.samucatezu.pimbackend.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceCategoryRepository extends JpaRepository<Category, Long> {

    Category findByCategoryName(String categoryName);
}
