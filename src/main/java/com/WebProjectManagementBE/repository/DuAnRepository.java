package com.WebProjectManagementBE.repository;

import com.WebProjectManagementBE.model.DuAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "duans", collectionResourceRel = "data")
public interface DuAnRepository extends JpaRepository<DuAn, String> {
  @Query("SELECT MAX(da.maDuAn) FROM DuAn da WHERE da.maDuAn LIKE :prefix")
  String findMaxMaDuAnByPrefix(@Param("prefix") String prefix);

}