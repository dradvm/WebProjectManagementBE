package com.WebProjectManagementBE.repository;

import com.WebProjectManagementBE.model.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "nguoidungs", collectionResourceRel = "data")
public interface NguoiDungRepository extends JpaRepository<NguoiDung, String> {
}
