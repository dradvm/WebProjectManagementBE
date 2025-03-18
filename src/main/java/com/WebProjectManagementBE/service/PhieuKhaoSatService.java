package com.WebProjectManagementBE.service;

import com.WebProjectManagementBE.model.PhieuKhaoSat;
import com.WebProjectManagementBE.repository.PhieuKhaoSatRepository;
import com.google.api.services.forms.v1.model.Form;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PhieuKhaoSatService {
    private final PhieuKhaoSatRepository phieuKhaoSatRepository;
    private final GoogleFormsService googleFormsService;

    @Autowired
    public PhieuKhaoSatService(PhieuKhaoSatRepository phieuKhaoSatRepository, GoogleFormsService googleFormsService) {
        this.phieuKhaoSatRepository = phieuKhaoSatRepository;
        this.googleFormsService = googleFormsService;
    }

    public List<PhieuKhaoSat> getAllPhieuKhaoSat() {
        return phieuKhaoSatRepository.findAll();
    }

    public Optional<PhieuKhaoSat> getPhieuKhaoSatById(String id) {
        return phieuKhaoSatRepository.findById(id);
    }

    public List<PhieuKhaoSat> searchPhieuKhaoSat(String title) {
        return phieuKhaoSatRepository.findByTenPhieuKhaoSatContaining(title);
    }

    public PhieuKhaoSat createPhieuKhaoSat(PhieuKhaoSat phieuKhaoSat) throws IOException {
        // Tạo form trên Google Forms
        Form googleForm = googleFormsService.createForm(
                phieuKhaoSat.getTenPhieuKhaoSat(), 
                "Phiếu khảo sát cho dự án: " + phieuKhaoSat.getMaDuAn().getTenDuAn()
        );
        
        // Cập nhật thông tin form
        phieuKhaoSat.setLienKet("https://docs.google.com/forms/d/" + googleForm.getFormId() + "/viewform");
        phieuKhaoSat.setLienKetTraLoi("https://docs.google.com/forms/d/" + googleForm.getFormId() + "/edit#responses");
        phieuKhaoSat.setNgayGioTao(new Date());
        
        return phieuKhaoSatRepository.save(phieuKhaoSat);
    }

    public PhieuKhaoSat updatePhieuKhaoSat(String id, PhieuKhaoSat phieuKhaoSatDetails) throws IOException {
        PhieuKhaoSat phieuKhaoSat = phieuKhaoSatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy phiếu khảo sát với ID: " + id));

        // Lấy formId từ lienKet
        String formId = extractFormId(phieuKhaoSat.getLienKet());
        if (formId != null) {
            // Cập nhật form trên Google Forms
            googleFormsService.updateForm(
                    formId,
                    phieuKhaoSatDetails.getTenPhieuKhaoSat(),
                    "Phiếu khảo sát cho dự án: " + phieuKhaoSatDetails.getMaDuAn().getTenDuAn()
            );
        }

        // Cập nhật thông tin trong database
        phieuKhaoSat.setTenPhieuKhaoSat(phieuKhaoSatDetails.getTenPhieuKhaoSat());
        phieuKhaoSat.setNgayGioMo(phieuKhaoSatDetails.getNgayGioMo());
        phieuKhaoSat.setNgayGioDong(phieuKhaoSatDetails.getNgayGioDong());
        phieuKhaoSat.setMaDuAn(phieuKhaoSatDetails.getMaDuAn());

        return phieuKhaoSatRepository.save(phieuKhaoSat);
    }

    public void deletePhieuKhaoSat(String id) throws IOException {
        PhieuKhaoSat phieuKhaoSat = phieuKhaoSatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy phiếu khảo sát với ID: " + id));

        // Lấy formId từ lienKet
        String formId = extractFormId(phieuKhaoSat.getLienKet());
        if (formId != null) {
            try {
                googleFormsService.deleteForm(formId);
            } catch (UnsupportedOperationException e) {
                // Log thông báo và tiếp tục xóa phiếu khảo sát trong database
                System.out.println("Không thể xóa form tự động. Vui lòng xóa thủ công: " + phieuKhaoSat.getLienKet());
            }
        }

        phieuKhaoSatRepository.delete(phieuKhaoSat);
    }

    private String extractFormId(String formUrl) {
        if (formUrl == null) return null;
        // URL format: https://docs.google.com/forms/d/[formId]/viewform
        String[] parts = formUrl.split("/");
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals("d") && i + 1 < parts.length) {
                return parts[i + 1];
            }
        }
        return null;
    }
} 