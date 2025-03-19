package com.WebProjectManagementBE.controller;

import com.WebProjectManagementBE.DTO.DuAnDTO;
import com.WebProjectManagementBE.model.DuAn;
import com.WebProjectManagementBE.model.NguoiDung;
import com.WebProjectManagementBE.model.QuanLyDuAn;
import com.WebProjectManagementBE.service.DuAnService;
import com.WebProjectManagementBE.service.FirebaseService;
import com.WebProjectManagementBE.service.NguoiDungService;
import com.WebProjectManagementBE.service.QuanLyDuAnService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/duan")
public class DuAnController {

    @Autowired
    DuAnService duAnService;

    @Autowired
    FirebaseService firebaseService;

    @Autowired
    NguoiDungService nguoiDungService;

    @Autowired
    QuanLyDuAnService quanLyDuAnService;


    @PostMapping("")
    public ResponseEntity<?> createDuAn(@RequestBody DuAnDTO duAnDTO) {
        Map<String, String> response = new HashMap<>();
        if (duAnService.createDuAn(duAnDTO) != null) {
            response.put("message", "Tạo dự án thành công");
            return ResponseEntity.ok(response);
        }
        else {
            response.put("message", "Lỗi không tạo được dự án");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/{maDuAn}")
    public ResponseEntity<?> getDuAn(@PathVariable String maDuAn) {
        if (duAnService.isNguoiDungInDuAn(maDuAn)) {
            return ResponseEntity.ok(duAnService.getDuAn(maDuAn));
        }
        else {
            return ResponseEntity.internalServerError().body("Không được phép truy cập");
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAllDuAnUser() {
        List<Map<String, Object>> duAns = new ArrayList<>();
        for (QuanLyDuAn qlda : quanLyDuAnService.getDuAnCollectionByNguoiDung(nguoiDungService.getCurrentSessionNguoiDung())) {
            Map<String, Object> data = new HashMap<>();
            data.put("owner", duAnService.getOwnerDuAn(qlda.getDuAn()));
            data.put("duan", qlda.getDuAn());
            duAns.add(data);
        }

        return ResponseEntity.ok(duAns);
    }

    @DeleteMapping("/{maDuAn}")
    public ResponseEntity<?> deleteDuAn(@PathVariable String maDuAn) {
        if (duAnService.isDuAnOwnedByUser(maDuAn)) {
            duAnService.deleteDuAn(maDuAn);
            return ResponseEntity.ok("Xoá dự án");
        }
        else {
            return ResponseEntity.internalServerError().body("Không được phép truy cập");
        }
    }

    @GetMapping("/listTrangThai")
    public ResponseEntity<?> getListTrangThai() {
        return ResponseEntity.ok(DuAnService.getTrangThaiValues());
    }


    @PostMapping("/addNguoiDungs")
    public ResponseEntity<?> addNguoiDungsDuAn(@RequestBody Map<String, Object> request) {
        String maDuAn = (String) request.get("maDuAn");
        List<String> maNguoiDungs = (List<String>) request.get("maNguoiDungs");
        if (duAnService.isDuAnOwnedByUser(maDuAn)) {
            DuAn duAn = duAnService.getDuAn(maDuAn);
            for (String maNguoiDung : maNguoiDungs) {
                NguoiDung nguoiDung = nguoiDungService.findByMaNguoiDung(maNguoiDung);
                duAnService.addNguoiDungDuAn(nguoiDung, duAn, false);
            }
            duAnService.updateDuAn(duAn);
            return ResponseEntity.ok("Thêm thành công");
        }
        else {
            return ResponseEntity.internalServerError().body("Không được phép truy cập");

        }

    }

    @GetMapping("/isDuAnOwner/{maDuAn}")
    public ResponseEntity<?> isDuAnOwner(@PathVariable String maDuAn) {
        return ResponseEntity.ok(duAnService.isDuAnOwnedByUser(maDuAn));
    }

}
