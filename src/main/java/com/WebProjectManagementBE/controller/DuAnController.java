package com.WebProjectManagementBE.controller;

import com.WebProjectManagementBE.DTO.DuAnDTO;
import com.WebProjectManagementBE.model.DuAn;
import com.WebProjectManagementBE.service.DuAnService;
import com.WebProjectManagementBE.service.FirebaseService;
import com.WebProjectManagementBE.service.NguoiDungService;
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

    @GetMapping("")
    public ResponseEntity<?> getAllDuAnUser() {
        List<Map<String, Object>> duAns = new ArrayList<>();
        for (DuAn da : nguoiDungService.getCurrentSessionNguoiDung().getDuAnCollection()) {
            Map<String, Object> data = new HashMap<>();
            data.put("owner", duAnService.getOwnerDuAn(da));
            data.put("duan", da);
            duAns.add(data);
        }

        return ResponseEntity.ok(duAns);
    }

    @DeleteMapping("/{maDuAn}")
    public ResponseEntity<?> deleteDuAn(@PathVariable String maDuAn) {
        duAnService.deleteDuAn(maDuAn);
        return ResponseEntity.ok("Xoá dự án");
    }

    @GetMapping("/listTrangThai")
    public ResponseEntity<?> getListTrangThai() {
        return ResponseEntity.ok(DuAnService.getTrangThaiValues());
    }

}
