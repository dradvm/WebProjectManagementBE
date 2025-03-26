package com.WebProjectManagementBE.service;

import com.WebProjectManagementBE.model.DuAn;
import com.WebProjectManagementBE.model.NguoiDung;
import com.WebProjectManagementBE.model.QuanLyDuAn;
import com.WebProjectManagementBE.model.Quyen;
import com.WebProjectManagementBE.repository.DuAnRepository;
import com.WebProjectManagementBE.repository.NguoiDungRepository;
import com.WebProjectManagementBE.repository.QuanLyDuAnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NguoiDungService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private QuyenService quyenService;
    @Autowired
    private DuAnRepository duAnRepository;
    @Autowired
    private QuanLyDuAnRepository quanLyDuAnRepository;

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

    public List<NguoiDung> findAllAccounts() { return nguoiDungRepository.findAll().stream()
            .filter(nguoiDung -> (nguoiDung.getMaQuyen().getMaQuyen().equals("Q1"))).toList();}

    public void updateNguoiDung(NguoiDung nguoiDung) { nguoiDungRepository.save(nguoiDung);}

    public NguoiDung getCurrentSessionNguoiDung() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String email = userDetails.getUsername();
        return nguoiDungRepository.findByEmail(email);
    }

    public List<NguoiDung> getNguoiDungNotInDuAn(String maDuAn) {
        DuAn duAn = duAnRepository.findById(maDuAn).orElseThrow();
        List<QuanLyDuAn> quanLyDuAns = (List<QuanLyDuAn>) duAn.getQuanLyDuAnCollection();
        Set<String> nguoiDungInDuAnIds = quanLyDuAns.stream()
                .map(ql -> ql.getNguoiDung().getMaNguoiDung())
                .collect(Collectors.toSet());
        return nguoiDungRepository.findAll().stream()
                .filter(nguoiDung -> (!nguoiDungInDuAnIds.contains(nguoiDung.getMaNguoiDung()) && nguoiDung.getMaQuyen().getMaQuyen().equals("Q1")))
                .collect(Collectors.toList());



    }
    public List<NguoiDung> getNguoiDungInDuAn(String maDuAn) {
        DuAn duAn = duAnRepository.findById(maDuAn).orElseThrow();
        List<NguoiDung> nguoiDungs = new ArrayList<>();

        for (QuanLyDuAn qlda : duAn.getQuanLyDuAnCollection()) {
            nguoiDungs.add(qlda.getNguoiDung());
        }

        return nguoiDungs;
    }
}
