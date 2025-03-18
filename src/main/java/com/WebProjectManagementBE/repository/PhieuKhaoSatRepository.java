package com.WebProjectManagementBE.repository;

import com.WebProjectManagementBE.model.PhieuKhaoSat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhieuKhaoSatRepository extends JpaRepository<PhieuKhaoSat, String> {
    List<PhieuKhaoSat> findByTenPhieuKhaoSatContaining(String title);
}