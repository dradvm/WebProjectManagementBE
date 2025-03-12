package com.WebProjectManagementBE.controller;


import com.WebProjectManagementBE.model.NguoiDung;
import com.WebProjectManagementBE.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountManagementController {

    @Autowired
    private NguoiDungService nguoiDungService;

    //Lấy dánh sách tất cả tài khoản
    @GetMapping
    public ResponseEntity<?> getAllAccounts(){
        List<NguoiDung> accounts = nguoiDungService.findAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    //Thêm tài khoản
    @PostMapping
    public ResponseEntity<?> addAccount(@RequestBody NguoiDung nguoiDung){
        nguoiDungService.registerNguoiDung(nguoiDung);
        return ResponseEntity.ok("Tài khoản được thêm thành công!");
    }

    //Chỉnh sửa tài khoản
    @PutMapping("/{maNguoiDung}")
    public ResponseEntity<?> updateAccount(@PathVariable String maNguoiDung,@RequestBody NguoiDung updatedNguoiDung){
        NguoiDung existingAccount = nguoiDungService.findByMaNguoiDung(maNguoiDung);
        if (existingAccount == null){
            return ResponseEntity.badRequest().body("Tài khoản không tồn tại!");
        }
        existingAccount.setHoTen(updatedNguoiDung.getHoTen());
        existingAccount.setSoDienThoai(updatedNguoiDung.getSoDienThoai());
        existingAccount.setLaNam(updatedNguoiDung.getLaNam());
        existingAccount.setMaQuyen(updatedNguoiDung.getMaQuyen());
        existingAccount.setMatKhau(updatedNguoiDung.getMatKhau());
        nguoiDungService.updateNguoiDung(existingAccount);
        return ResponseEntity.ok("Cập nhật thông tin tài khoản thành công!");
    }

    //Vô hiệu hóa tài khoản
    @DeleteMapping("/{maNguoiDung}")
    public ResponseEntity<?> disableAccount(@PathVariable String maNguoiDung){
        NguoiDung account = nguoiDungService.findByMaNguoiDung(maNguoiDung);
        if (account == null){
            return ResponseEntity.badRequest().body("Tài khoản không tồn tại!");
        }
        account.setActive(false);
        nguoiDungService.updateNguoiDung(account);
        return ResponseEntity.ok("Tài khoản đã bị vô hiệu hóa!");
    }
}
