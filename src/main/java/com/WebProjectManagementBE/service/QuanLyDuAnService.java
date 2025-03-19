package com.WebProjectManagementBE.service;

import com.WebProjectManagementBE.model.NguoiDung;
import com.WebProjectManagementBE.model.QuanLyDuAn;
import com.WebProjectManagementBE.repository.QuanLyDuAnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuanLyDuAnService {
    @Autowired
    QuanLyDuAnRepository quanLyDuAnRepository;

    public List<QuanLyDuAn> getDuAnCollectionByNguoiDung(NguoiDung nguoiDung) {
        return quanLyDuAnRepository.findByMaNguoiDung(nguoiDung.getMaNguoiDung());
    }

}
