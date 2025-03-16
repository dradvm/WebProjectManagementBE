package com.WebProjectManagementBE.service;

import com.WebProjectManagementBE.model.NguoiDung;
import com.WebProjectManagementBE.model.Quyen;
import com.WebProjectManagementBE.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NguoiDungService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private QuyenService quyenService;

    public String generateNewMaNguoiDung(Quyen quyen) {
        String prefix = "";
        if (quyen == quyenService.getQuyenNguoiDung()) {
            prefix = "ND";
        } else if (quyen == quyenService.getQuyenQuanTriVien()) {
            prefix = "QTV";
        } else {
            return "";
        }

        int length = 9;

        // Truy vấn mã lớn nhất có tiền tố cụ thể
        String maxCode = nguoiDungRepository.findMaxMaNguoiDungByPrefix(prefix + "%");

        int newNumber = 1;
        if (maxCode != null) {
            String numberPart = maxCode.substring(prefix.length());
            newNumber = Integer.parseInt(numberPart) + 1;
        }

        return prefix + String.format("%0" + length + "d", newNumber);
    }

    public boolean isSoDienThoaiExist(String soDienThoai) {
        return nguoiDungRepository.existsBySoDienThoai(soDienThoai);
    }

    public boolean isEmailExist(String email) {
        return nguoiDungRepository.existsByEmail(email);
    }


    public void registerNguoiDung(NguoiDung NguoiDung) {
        nguoiDungRepository.save(NguoiDung);
    }

    public NguoiDung findByEmail(String email) {
        return nguoiDungRepository.findByEmail(email);
    }
    public NguoiDung findByMaNguoiDung(String maNguoiDung) {
        return nguoiDungRepository.findByMaNguoiDung(maNguoiDung);
    }

    public List<NguoiDung> findAllAccounts() { return nguoiDungRepository.findAll();}

    public void updateNguoiDung(NguoiDung nguoiDung) { nguoiDungRepository.save(nguoiDung);}
}
