package com.WebProjectManagementBE.repository;

import com.WebProjectManagementBE.model.TapTin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TapTinRepository extends JpaRepository<TapTin, String> {
    @Query("SELECT MAX(tt.maTapTin) FROM TapTin tt WHERE tt.maTapTin LIKE :prefix")
    String findMaxMaTapTinByPrefix(@Param("prefix") String prefix);
}