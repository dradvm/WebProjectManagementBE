package com.WebProjectManagementBE.repository;

import com.WebProjectManagementBE.model.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RepositoryRestResource(path = "nguoidungs", collectionResourceRel = "data")
public interface NguoiDungRepository extends JpaRepository<NguoiDung, String> {

    @Query("SELECT MAX(nd.maNguoiDung) FROM NguoiDung nd WHERE nd.maNguoiDung LIKE :prefix")
    String findMaxMaNguoiDungByPrefix(@Param("prefix") String prefix);

    boolean existsBySoDienThoai(String soDienThoai);
    boolean existsByEmail(String email);
    NguoiDung findByEmail(String email);
    NguoiDung findByMaNguoiDung(String maNguoiDung);
    List<NguoiDung> findByActiveTrue();
    List<NguoiDung> findByActiveFalse();

}
