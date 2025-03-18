package com.WebProjectManagementBE.service;

import com.WebProjectManagementBE.DTO.DuAnDTO;
import com.WebProjectManagementBE.model.DuAn;
import com.WebProjectManagementBE.model.NguoiDung;
import com.WebProjectManagementBE.model.Quyen;
import com.WebProjectManagementBE.model.TapTin;
import com.WebProjectManagementBE.repository.DuAnRepository;
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
        return Arrays.stream(TrangThai.values()) // Lấy tất cả giá trị của enum
                .map(TrangThai::getValue) // Lấy giá trị `value`
                .collect(Collectors.toList()); // Chuyển thành danh sách
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


        List<NguoiDung> currentNguoiDungCollection = (List<NguoiDung>) duAn.getNguoiDungCollection();
        if (currentNguoiDungCollection == null) {
            currentNguoiDungCollection = new ArrayList<NguoiDung>();
        }
        currentNguoiDungCollection.add(nguoiDungService.getCurrentSessionNguoiDung());
        duAn.setNguoiDungCollection(currentNguoiDungCollection);

        duAnRepository.save(duAn);
        return duAn;
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
        boolean check = false;

        for (DuAn da : user.getDuAnCollection()) {
            if (da.getMaDuAn().equals(maDuAn)) {
                check = true;
                break;
            }
        }
        return check;
    }

    public String getOwnerDuAn(DuAn duAn) {
        return ((List<NguoiDung>) duAn.getNguoiDungCollection()).get(0).getHoTen();
    }
}
