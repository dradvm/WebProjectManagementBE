package com.WebProjectManagementBE.controller;

import com.WebProjectManagementBE.model.PhieuKhaoSat;
import com.WebProjectManagementBE.service.PhieuKhaoSatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/phieuKhaoSat")
@CrossOrigin(origins = "*")
public class PhieuKhaoSatController {

    @Autowired
    private PhieuKhaoSatService phieuKhaoSatService;

    /**
     * Lấy danh sách tất cả phiếu khảo sát
     */
    @GetMapping
    public List<PhieuKhaoSat> getAllPhieuKhaoSat() {
        return phieuKhaoSatService.getAllPhieuKhaoSat();
    }

    /**
     * Tìm kiếm phiếu khảo sát theo tiêu đề
     */
    @GetMapping("/search")
    public List<PhieuKhaoSat> searchPhieuKhaoSat(@RequestParam String title) {
        return phieuKhaoSatService.searchPhieuKhaoSat(title);
    }

    /**
     * Lấy chi tiết một phiếu khảo sát
     */
    @GetMapping("/{id}")
    public ResponseEntity<PhieuKhaoSat> getPhieuKhaoSatById(@PathVariable String id) {
        return phieuKhaoSatService.getPhieuKhaoSatById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Tạo phiếu khảo sát mới
     */
    @PostMapping
    public ResponseEntity<?> createPhieuKhaoSat(@RequestBody PhieuKhaoSat phieuKhaoSat) {
        try {
            PhieuKhaoSat createdPhieuKhaoSat = phieuKhaoSatService.createPhieuKhaoSat(phieuKhaoSat);
            return ResponseEntity.ok(createdPhieuKhaoSat);
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body("Lỗi khi tạo Google Form: " + e.getMessage());
        }
    }

    /**
     * Cập nhật phiếu khảo sát
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePhieuKhaoSat(
            @PathVariable String id,
            @RequestBody PhieuKhaoSat phieuKhaoSat) {
        try {
            PhieuKhaoSat updatedPhieuKhaoSat = phieuKhaoSatService.updatePhieuKhaoSat(id, phieuKhaoSat);
            return ResponseEntity.ok(updatedPhieuKhaoSat);
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body("Lỗi khi cập nhật Google Form: " + e.getMessage());
        }
    }

    /**
     * Xóa phiếu khảo sát
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePhieuKhaoSat(@PathVariable String id) {
        try {
            phieuKhaoSatService.deletePhieuKhaoSat(id);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body("Lỗi khi xóa Google Form: " + e.getMessage());
        }
    }
}