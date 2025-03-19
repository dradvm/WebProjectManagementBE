package com.WebProjectManagementBE.service;

import com.WebProjectManagementBE.DTO.DuAnDTO;
import com.WebProjectManagementBE.model.*;
import com.WebProjectManagementBE.repository.DuAnRepository;
import com.WebProjectManagementBE.repository.QuanLyDuAnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DuAnService {
    @Autowired
    DuAnRepository duAnRepository;

    @Autowired
    TapTinService tapTinService;

    @Autowired
    NguoiDungService nguoiDungService;

    @Autowired
    QuanLyDuAnRepository quanLyDuAnRepository;

    public enum TrangThai {
        NOTSTARTING("Chưa bắt đầu"),
        ACTIVE("Đang thực hiện"),
        CANCELLED("Không hoàn thành"),
        COMPLETED("Hoàn thành");

        private final String value;

        TrangThai(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
    public static List<String> getTrangThaiValues() {
        return Arrays.stream(TrangThai.values())
                .map(TrangThai::getValue)
                .collect(Collectors.toList());
    }

    public DuAn createDuAn(DuAnDTO duAnDTO) {
        DuAn duAn = new DuAn();
        duAn.setMaDuAn(generateNewMaDuAn());
        duAn.setTenDuAn(duAnDTO.getTenDuAn());
        duAn.setMoTa(duAnDTO.getMoTa());
        duAn.setNgayBatDau(duAnDTO.getNgayBatDau());
        duAn.setNgayKetThuc(duAnDTO.getNgayKetThuc());
        duAn.setTienDoHoanThanh(0.0);
        duAn.setTrangThai(TrangThai.ACTIVE.getValue());


        duAnRepository.save(duAn);
        addNguoiDungDuAn(nguoiDungService.getCurrentSessionNguoiDung(), duAn, true);

        return duAn;
    }

    public void addNguoiDungDuAn(NguoiDung nguoiDung, DuAn duAn, boolean isChuDuAn) {
        QuanLyDuAnPK quanLyDuAnPK = new QuanLyDuAnPK(nguoiDung.getMaNguoiDung(), duAn.getMaDuAn());
        QuanLyDuAn quanLyDuAn = new QuanLyDuAn();
        quanLyDuAn.setQuanLyDuAnPK(quanLyDuAnPK);
        quanLyDuAn.setNguoiDung(nguoiDung);
        quanLyDuAn.setDuAn(duAn);
        quanLyDuAn.setChuDuAn(isChuDuAn);

        quanLyDuAnRepository.save(quanLyDuAn);
    }
    public void updateDuAn(DuAn duAn) {
        duAnRepository.save(duAn);
    }

    public String generateNewMaDuAn() {
        String prefix = "DUAN";
        int length = 9;

        String maxCode = duAnRepository.findMaxMaDuAnByPrefix(prefix + "%");

        int newNumber = 1;
        if (maxCode != null) {
            String numberPart = maxCode.substring(prefix.length());
            newNumber = Integer.parseInt(numberPart) + 1;
        }

        return prefix + String.format("%0" + length + "d", newNumber);
    }
    public List<DuAn> getAllDuAn() {
        return duAnRepository.findAll();
    }

    public void deleteDuAn(String maDuAn) {
        DuAn duAn = duAnRepository.findById(maDuAn).orElseThrow();
        for (TapTin tapTin : duAn.getTapTinCollection()) {
            tapTinService.deleteTapTin(tapTin);
        }

        duAnRepository.delete(duAn);
    }

    public DuAn getDuAn(String maDuAn) {
        return duAnRepository.findById(maDuAn).orElseThrow();
    }

    public boolean isDuAnOwnedByUser(String maDuAn) {
        NguoiDung user = nguoiDungService.getCurrentSessionNguoiDung();

        return quanLyDuAnRepository.findById(new QuanLyDuAnPK(user.getMaNguoiDung(), maDuAn)).orElseThrow().getChuDuAn();
    }

    public boolean isNguoiDungInDuAn(String maDuAn) {
        NguoiDung user = nguoiDungService.getCurrentSessionNguoiDung();

        return quanLyDuAnRepository.existsById(new QuanLyDuAnPK(user.getMaNguoiDung(), maDuAn));
    }

    public String getOwnerDuAn(DuAn duAn) {
        for (QuanLyDuAn quanLyDuAn : duAn.getQuanLyDuAnCollection()) {
            if (quanLyDuAn.getChuDuAn()) {
                return quanLyDuAn.getNguoiDung().getHoTen();
            }
        }
        return "";
    }


}
