package com.WebProjectManagementBE.repository;

import com.WebProjectManagementBE.model.QuanLyDuAn;
import com.WebProjectManagementBE.model.QuanLyDuAnPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuanLyDuAnRepository extends JpaRepository<QuanLyDuAn, QuanLyDuAnPK> {
    List<QuanLyDuAn> findByMaNguoiDung(String maNguoiDung);

}