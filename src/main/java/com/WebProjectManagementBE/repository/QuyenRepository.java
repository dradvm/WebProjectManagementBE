/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.WebProjectManagementBE.repository;
import com.WebProjectManagementBE.model.Quyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author DUY
 */
@RepositoryRestResource(path = "quyens", collectionResourceRel = "data")
public interface QuyenRepository extends JpaRepository<Quyen, String>{

}
