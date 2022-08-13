package com.samucatezu.pimbackend.Repository;


import com.samucatezu.pimbackend.Model.SecurityCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityCategoryRepository extends JpaRepository<SecurityCategory, Integer> {

    SecurityCategory findByCategoryName(String categoryName);
}
