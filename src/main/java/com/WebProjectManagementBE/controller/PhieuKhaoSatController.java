package com.WebProjectManagementBE.controller;

import com.WebProjectManagementBE.model.PhieuKhaoSat;
import com.WebProjectManagementBE.service.PhieuKhaoSatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phieuKhaoSat")
public class PhieuKhaoSatController {

    @Autowired
    private PhieuKhaoSatService phieuKhaoSatService;

    /**
     * Lấy danh sách tất cả phiếu khảo sát.
     *
     * @return Danh sách các phiếu khảo sát.
     */
    @GetMapping
    public List<PhieuKhaoSat> getAllPhieuKhaoSat() {
        return phieuKhaoSatService.getAllPhieuKhaoSat();
    }

    /**
     * Lấy phiếu khảo sát theo ID.
     *
     * @param id Mã ID của phiếu khảo sát.
     * @return Phiếu khảo sát tìm được hoặc trả về lỗi NOT_FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PhieuKhaoSat> getPhieuKhaoSatById(@PathVariable String id) {
        return phieuKhaoSatService.getPhieuKhaoSatById(id)
                .map(ResponseEntity::ok) // Nếu tìm thấy, trả về ResponseEntity.ok
                .orElse(ResponseEntity.notFound().build()); // Nếu không tìm thấy, trả về NOT_FOUND
    }

    /**
     * Tạo một phiếu khảo sát mới.
     *
     * @param phieuKhaoSat Thông tin phiếu khảo sát cần tạo.
     * @return Phiếu khảo sát đã được tạo và lưu vào cơ sở dữ liệu.
     */
    @PostMapping
    public ResponseEntity<PhieuKhaoSat> createPhieuKhaoSat(@RequestBody PhieuKhaoSat phieuKhaoSat) {
        // Gọi service để tạo phiếu khảo sát và liên kết Google Form
        PhieuKhaoSat createdPhieuKhaoSat = phieuKhaoSatService.createPhieuKhaoSat(phieuKhaoSat);
        return ResponseEntity.ok(createdPhieuKhaoSat);
    }

    /**
     * Cập nhật phiếu khảo sát theo ID.
     *
     * @param id           Mã ID của phiếu khảo sát cần cập nhật.
     * @param phieuKhaoSat Nội dung thông tin cần cập nhật.
     * @return Phiếu khảo sát sau khi được cập nhật hoặc lỗi nếu không tìm thấy ID.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<PhieuKhaoSat> updatePhieuKhaoSat(
            @PathVariable String id,
            @RequestBody PhieuKhaoSat phieuKhaoSat) {
        try {
            PhieuKhaoSat updatedPhieuKhaoSat = phieuKhaoSatService.updatePhieuKhaoSat(id, phieuKhaoSat);
            return ResponseEntity.ok(updatedPhieuKhaoSat);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build(); // Nếu không tìm thấy phiếu khảo sát
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(null); // Các lỗi khác
        }
    }

    /**
     * Xóa phiếu khảo sát theo ID.
     *
     * @param id Mã ID của phiếu khảo sát cần xóa.
     * @return Trả về trạng thái NO_CONTENT sau khi hoàn thành xóa thành công.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhieuKhaoSat(@PathVariable String id) {
        phieuKhaoSatService.deletePhieuKhaoSat(id);
        return ResponseEntity.noContent().build();
    }
}