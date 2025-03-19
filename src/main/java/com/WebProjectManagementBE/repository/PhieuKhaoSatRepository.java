package com.WebProjectManagementBE.repository;

import com.WebProjectManagementBE.model.PhieuKhaoSat;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuKhaoSatRepository extends JpaRepository<PhieuKhaoSat, String> {
    Optional<PhieuKhaoSat> findByMaPhieuKhaoSat(String maPhieuKhaoSat);
    List<PhieuKhaoSat> findByTenPhieuKhaoSatContaining(String title);
    List<PhieuKhaoSat> findByTenPhieuKhaoSatContainingIgnoreCase(String keyword);
    List<PhieuKhaoSat> findByMaDuAn_MaDuAn(String maDuAn);
}