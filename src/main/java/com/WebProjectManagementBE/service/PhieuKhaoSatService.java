package com.WebProjectManagementBE.service;

import com.WebProjectManagementBE.model.PhieuKhaoSat;
import com.WebProjectManagementBE.repository.PhieuKhaoSatRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class PhieuKhaoSatService {
    private final PhieuKhaoSatRepository phieuKhaoSatRepository;
    private final GoogleFormsService googleFormsService;

    @Autowired
    public PhieuKhaoSatService(
            PhieuKhaoSatRepository phieuKhaoSatRepository,
            GoogleFormsService googleFormsService) {
        this.phieuKhaoSatRepository = phieuKhaoSatRepository;
        this.googleFormsService = googleFormsService;
    }

    // Lấy danh sách phiếu khảo sát theo mã dự án
    public List<PhieuKhaoSat> getPhieuKhaoSatByMaDuAn(String maDuAn) {
        return phieuKhaoSatRepository.findByMaDuAn_MaDuAn(maDuAn);
    }

    // Lấy tất cả phiếu khảo sát
    public List<PhieuKhaoSat> getAllPhieuKhaoSat() {
        return phieuKhaoSatRepository.findAll();
    }

    // Tìm kiếm phiếu khảo sát theo từ khóa
    public List<PhieuKhaoSat> searchPhieuKhaoSat(String keyword) {
        return phieuKhaoSatRepository.findByTenPhieuKhaoSatContainingIgnoreCase(keyword);
    }

    // Lấy phiếu khảo sát theo ID
    public PhieuKhaoSat getPhieuKhaoSatById(String maPhieuKhaoSat) {
        return phieuKhaoSatRepository.findById(maPhieuKhaoSat)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy phiếu khảo sát với ID: " + maPhieuKhaoSat));
    }

    // Thêm phương thức tạo mã phiếu khảo sát
    private String generateMaPhieuKhaoSat() {
        String prefix = "PKS";
        long count = phieuKhaoSatRepository.count() + 1;
        return String.format("%s%03d", prefix, count);
    }

    // Tạo phiếu khảo sát mới
    @Transactional
    public PhieuKhaoSat createPhieuKhaoSat(PhieuKhaoSat phieuKhaoSat) throws IOException {
        // Tạo mã phiếu khảo sát
        phieuKhaoSat.setMaPhieuKhaoSat(generateMaPhieuKhaoSat());
        
        // Tạo form trong Google Forms
        String formId = googleFormsService.createForm(
            phieuKhaoSat.getTenPhieuKhaoSat(),
            "Phiếu khảo sát cho dự án: " + phieuKhaoSat.getMaDuAn().getTenDuAn()
        );
        // Cập nhật thông tin form
        phieuKhaoSat.setLienKet("https://docs.google.com/forms/d/" + formId + "/viewform");
        phieuKhaoSat.setLienKetTraLoi("https://docs.google.com/forms/d/" + formId + "/edit#responses");
        
        // Lưu vào database
        return phieuKhaoSatRepository.save(phieuKhaoSat);
    }

    // Lấy formId từ lienKet
    private String extractFormIdFromLink(String lienKet) {
        if (lienKet == null || !lienKet.contains("/d/")) {
            throw new IllegalArgumentException("Liên kết không hợp lệ");
        }
        return lienKet.split("/d/")[1].split("/")[0];
    }

    // Cập nhật phiếu khẻo sát
    @Transactional
    public PhieuKhaoSat updatePhieuKhaoSat(String maPhieuKhaoSat, PhieuKhaoSat phieuKhaoSat) throws IOException {
        PhieuKhaoSat existingPhieuKhaoSat = getPhieuKhaoSatById(maPhieuKhaoSat);

        // Cập nhật thông tin cơ bản
        existingPhieuKhaoSat.setTenPhieuKhaoSat(phieuKhaoSat.getTenPhieuKhaoSat());
        existingPhieuKhaoSat.setNgayGioMo(phieuKhaoSat.getNgayGioMo());
        existingPhieuKhaoSat.setNgayGioDong(phieuKhaoSat.getNgayGioDong());

        // Lấy formId từ lienKet và cập nhật form Google Forms
        if (existingPhieuKhaoSat.getLienKet() != null) {
            String formId = extractFormIdFromLink(existingPhieuKhaoSat.getLienKet());
            googleFormsService.updateForm(formId, phieuKhaoSat.getTenPhieuKhaoSat(), "");
        }

        return phieuKhaoSatRepository.save(existingPhieuKhaoSat);
    }

    // Xóa phiếu khảo sát
    @Transactional
    public void deletePhieuKhaoSat(String maPhieuKhaoSat) throws IOException {
        PhieuKhaoSat phieuKhaoSat = getPhieuKhaoSatById(maPhieuKhaoSat);
        // Xóa trong database
        phieuKhaoSatRepository.delete(phieuKhaoSat);
    }

    // Lấy kết quả khảo sát từ Google Forms
    public Object getKetQuaKhaoSat(String maPhieuKhaoSat) throws IOException {
        return googleFormsService.getFormResponses(maPhieuKhaoSat);
    }
} 