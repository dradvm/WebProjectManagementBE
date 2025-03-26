package com.WebProjectManagementBE.pattern.factory;

import com.WebProjectManagementBE.DTO.NguoiDungRegisterDTO;
import com.WebProjectManagementBE.model.NguoiDung;
import com.WebProjectManagementBE.model.Quyen;
import com.WebProjectManagementBE.service.NguoiDungService;
import com.WebProjectManagementBE.service.PasswordService;
import com.WebProjectManagementBE.service.QuyenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NguoiDungFactory {

    @Autowired
    private NguoiDungService nguoiDungService;

    @Autowired
    private QuyenService quyenService;

    @Autowired
    private PasswordService passwordService;


    public NguoiDung createNguoiDung(NguoiDungRegisterDTO dto) {
        NguoiDung nguoiDung = new NguoiDung();
        Quyen quyen = quyenService.getQuyenNguoiDung();
        setValueNguoiDung(nguoiDung, dto, quyen);
        return nguoiDung;
    }

    public NguoiDung createQuanTriVien(NguoiDungRegisterDTO dto) {
        NguoiDung nguoiDung = new NguoiDung();
        Quyen quyen = quyenService.getQuyenQuanTriVien();
        setValueNguoiDung(nguoiDung, dto, quyen);
        return nguoiDung;
    }

    private void setValueNguoiDung(NguoiDung nguoiDung, NguoiDungRegisterDTO dto, Quyen quyen) {
        nguoiDung.setMaNguoiDung(nguoiDungService.generateNewMaNguoiDung(quyen));
        nguoiDung.setHoTen(dto.getHoTen());
        nguoiDung.setLaNam(dto.isLaNam());
        nguoiDung.setSoDienThoai(dto.getSoDienThoai());
        nguoiDung.setEmail(dto.getEmail());
        nguoiDung.setMatKhau(passwordService.encodePassword(dto.getMatKhau()));
        nguoiDung.setMaQuyen(quyenService.getQuyenNguoiDung());
    }


}
