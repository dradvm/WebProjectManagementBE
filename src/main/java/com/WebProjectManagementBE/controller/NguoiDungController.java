package com.WebProjectManagementBE.controller;


import com.WebProjectManagementBE.DTO.NguoiDungRegisterDTO;
import com.WebProjectManagementBE.model.NguoiDung;
import com.WebProjectManagementBE.pattern.chainOfResponsibility.EmailNguoiDungValidator;
import com.WebProjectManagementBE.pattern.chainOfResponsibility.NguoiDungValidator;
import com.WebProjectManagementBE.pattern.chainOfResponsibility.SoDienThoaiNguoiDungValidator;
import com.WebProjectManagementBE.pattern.factory.NguoiDungFactory;
import com.WebProjectManagementBE.service.DuAnService;
import com.WebProjectManagementBE.service.NguoiDungService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class NguoiDungController {

    @Autowired
    private NguoiDungService nguoiDungService;

    @Autowired
    DuAnService duAnService;

    @Autowired
    NguoiDungFactory nguoiDungFactory;

    //Lấy dánh sách tất cả tài khoản
    @GetMapping
    public ResponseEntity<?> getAllAccounts(){
        List<NguoiDung> accounts = nguoiDungService.findAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    //Thêm tài khoản
    @PostMapping
    public ResponseEntity<?> registerNguoiDung(@RequestBody NguoiDung nguoiDung) {
        nguoiDungService.registerNguoiDung(nguoiDung);
        return ResponseEntity.ok("Tài khoản được thêm thành công!");
    }

    //Chỉnh sửa tài khoản
    @PatchMapping("/{maNguoiDung}")
    public ResponseEntity<?> updateAccount(@PathVariable String maNguoiDung, @RequestBody NguoiDung updatedNguoiDung) {
        try {
            // Lấy thông tin tài khoản hiện tại
            NguoiDung existingNguoiDung = nguoiDungService.findByMaNguoiDung(maNguoiDung);

            if (existingNguoiDung == null) {
                return ResponseEntity.badRequest().body("Tài khoản không tồn tại!");
            }

            // Kiểm tra nếu giá trị mới của email hoặc số điện thoại đã được sử dụng bởi tài khoản khác
            if (!existingNguoiDung.getEmail().equals(updatedNguoiDung.getEmail())) {
                boolean isDuplicateEmail = nguoiDungService.isEmailExist(updatedNguoiDung.getEmail());
                if (isDuplicateEmail) {
                    return ResponseEntity.badRequest().body("Email đã được sử dụng bởi tài khoản khác!");
                }
            }

            if (!existingNguoiDung.getSoDienThoai().equals(updatedNguoiDung.getSoDienThoai())) {
                boolean isDuplicatePhone = nguoiDungService.isSoDienThoaiExist(updatedNguoiDung.getSoDienThoai());
                if (isDuplicatePhone) {
                    return ResponseEntity.badRequest().body("Số điện thoại đã được sử dụng bởi tài khoản khác!");
                }
            }

            // Chỉ cập nhật các trường được thay đổi
            existingNguoiDung.setHoTen(updatedNguoiDung.getHoTen());
            existingNguoiDung.setSoDienThoai(updatedNguoiDung.getSoDienThoai());
            existingNguoiDung.setEmail(updatedNguoiDung.getEmail());
            existingNguoiDung.setLaNam(updatedNguoiDung.getLaNam());
            existingNguoiDung.setMaQuyen(updatedNguoiDung.getMaQuyen());
            nguoiDungService.updateNguoiDung(existingNguoiDung);

            return ResponseEntity.ok("Cập nhật thông tin tài khoản thành công!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Lỗi: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Đã xảy ra lỗi trong hệ thống: " + e.getMessage());
        }
    }

    //Chuyển đổi trạng thái tài khoản (Vô hiệu hóa/Kích hoạt)
    @PatchMapping("/{maNguoiDung}/active/{isActive}")
    public ResponseEntity<?> toggleAccountStatus(
            @PathVariable String maNguoiDung,
            @PathVariable boolean isActive
    ) {
        NguoiDung account = nguoiDungService.findByMaNguoiDung(maNguoiDung);
        if (account == null) {
            return ResponseEntity.badRequest().body("Tài khoản không tồn tại!");
        }

        account.setActive(isActive);
        nguoiDungService.updateNguoiDung(account);

        String status = isActive ? "kích hoạt" : "vô hiệu hóa";
        return ResponseEntity.ok("Tài khoản đã được " + status + " thành công!");
    }


    @GetMapping("/listNguoiDungNotInDuAn/{maDuAn}")
    public ResponseEntity<?> getListNguoiDungNotInDuAn(@PathVariable String maDuAn) {

        if (duAnService.isDuAnOwnedByUser(maDuAn)) {
            List<NguoiDung> nguoiDungs = nguoiDungService.getNguoiDungNotInDuAn(maDuAn);
            return ResponseEntity.ok(nguoiDungs);
        }
        else {
            return ResponseEntity.internalServerError().body("Không được phép truy cập");
        }



    }

    @GetMapping("/listNguoiDungInDuAn/{maDuAn}")
    public ResponseEntity<?> getListNguoiDungInDuAn(@PathVariable String maDuAn) {
        List<NguoiDung> nguoiDungs = nguoiDungService.getNguoiDungInDuAn(maDuAn);
        return ResponseEntity.ok(nguoiDungs);
    }
}
