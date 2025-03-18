package com.WebProjectManagementBE.service;

import com.WebProjectManagementBE.model.TapTin;
import com.WebProjectManagementBE.pattern.templateMethod.DefaultFileUploader;
import com.WebProjectManagementBE.repository.DuAnRepository;
import com.WebProjectManagementBE.repository.LoaiTapTinRepository;
import com.WebProjectManagementBE.repository.TapTinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
public class TapTinService {

    @Autowired
    TapTinRepository tapTinRepository;

    @Autowired
    LoaiTapTinService loaiTapTinService;

    @Autowired
    DuAnRepository duAnRepository;

    @Autowired
    FirebaseService firebaseService;

    public TapTin createTapTin(String maDuAn, String filename, MultipartFile file) {
        TapTin tapTin = new TapTin();
        tapTin.setMaTapTin(generateNewMaTapTin());
        tapTin.setTenTapTin(file.getOriginalFilename());
        tapTin.setLienKet(filename);
        tapTin.setNgayTao(LocalDate.now());
        tapTin.setMaLoai(loaiTapTinService.getLoaiTapTin(loaiTapTinService.determineLoaiTapTin(file)));
        tapTin.setMaDuAn(duAnRepository.findById(maDuAn).orElseThrow());
        tapTinRepository.save(tapTin);
        return tapTin;
    }

    public void deleteTapTin(TapTin tapTin) {
        firebaseService.deleteFile(tapTin.getLienKet());
    }


    public String generateNewMaTapTin() {
        String prefix = "TT";
        int length = 9;

        String maxCode = tapTinRepository.findMaxMaTapTinByPrefix(prefix + "%");

        int newNumber = 1;
        if (maxCode != null) {
            String numberPart = maxCode.substring(prefix.length());
            newNumber = Integer.parseInt(numberPart) + 1;
        }

        return prefix + String.format("%0" + length + "d", newNumber);
    }
}
