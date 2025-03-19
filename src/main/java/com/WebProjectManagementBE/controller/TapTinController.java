package com.WebProjectManagementBE.controller;

import com.WebProjectManagementBE.model.DuAn;
import com.WebProjectManagementBE.model.NguoiDung;
import com.WebProjectManagementBE.model.TapTin;
import com.WebProjectManagementBE.pattern.templateMethod.DefaultFileUploader;
import com.WebProjectManagementBE.service.DuAnService;
import com.WebProjectManagementBE.service.FirebaseService;
import com.WebProjectManagementBE.service.NguoiDungService;
import com.WebProjectManagementBE.service.TapTinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/taptin")
public class TapTinController {

    @Autowired
    DefaultFileUploader defaultFileUploader;

    @Autowired
    FirebaseService firebaseService;

    @Autowired
    NguoiDungService nguoiDungService;

    @Autowired
    DuAnService duAnService;

    @PostMapping("/uploadFiles")
    public ResponseEntity<?> uploadFilesDuAn(@RequestParam("files") List<MultipartFile> files, @RequestParam("maDuAn") String maDuAn) {

        try {
            for (MultipartFile file : files) {
                String filename = firebaseService.generateFileNameDuAn(maDuAn, file);
                defaultFileUploader.uploadFile(maDuAn, filename, file);
            }
            return ResponseEntity.ok("Thành công");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Lỗi");
        }
    }

    @GetMapping("/duAnTapTinCollection/{maDuAn}")
    public ResponseEntity<?> getDuAnTapTinCollection(@PathVariable String maDuAn) {

        DuAn duAn = duAnService.getDuAn(maDuAn);
        List<TapTin> tapTins = (List<TapTin>) duAn.getTapTinCollection();
        for (TapTin tt : tapTins) {
            tt.setLienKet(firebaseService.getDownloadUrl(tt.getLienKet()));
        }
        return ResponseEntity.ok(tapTins);
    }
}
