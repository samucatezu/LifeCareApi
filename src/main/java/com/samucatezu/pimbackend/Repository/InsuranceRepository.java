package com.samucatezu.pimbackend.Repository;

import com.samucatezu.pimbackend.Model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {
}
