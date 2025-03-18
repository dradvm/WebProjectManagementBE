package com.WebProjectManagementBE.service;

import com.WebProjectManagementBE.model.PhieuKhaoSat;
import com.WebProjectManagementBE.repository.PhieuKhaoSatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PhieuKhaoSatService {

    @Autowired
    private PhieuKhaoSatRepository phieuKhaoSatRepository;

    @Autowired
    private GoogleFormWebhookService googleFormWebhookService;

    /**
     * Tạo phiếu khảo sát mới, đồng thời tích hợp với Google Form webhook.
     *
     * @param phieuKhaoSat Thông tin phiếu khảo sát cần thêm mới.
     * @return Đối tượng phiếu khảo sát đã được thêm vào cơ sở dữ liệu.
     */
    public PhieuKhaoSat createPhieuKhaoSat(PhieuKhaoSat phieuKhaoSat) {
        // Gọi webhook để tạo Google Form
        Map<String, String> googleFormLinks = googleFormWebhookService.createGoogleForm(
                phieuKhaoSat.getTenPhieuKhaoSat(),
                "Phiếu khảo sát tự động tạo qua hệ thống" // Có thể tùy chỉnh nội dung hoặc lấy từ đầu vào
        );

        // Cập nhật URL Google Form vào đối tượng phiếu khảo sát
        phieuKhaoSat.setLienKet(googleFormLinks.get("formEditUrl")); // URL chỉnh sửa Google Form
        phieuKhaoSat.setLienKetTraLoi(googleFormLinks.get("formResponseUrl")); // URL phản hồi khảo sát

        // Thêm các thông tin khác (nếu cần) trước khi lưu vào cơ sở dữ liệu
        phieuKhaoSat.setNgayGioTao(new Date()); // Thời gian tạo phiếu

        // Lưu vào cơ sở dữ liệu
        return phieuKhaoSatRepository.save(phieuKhaoSat);
    }

    /**
     * Cập nhật phiếu khảo sát theo ID.
     *
     * @param id           Mã ID của phiếu khảo sát cần cập nhật.
     * @param phieuKhaoSat Nội dung cập nhật.
     * @return Phiếu khảo sát sau khi được cập nhật.
     */
    public PhieuKhaoSat updatePhieuKhaoSat(String id, PhieuKhaoSat phieuKhaoSat) {
        // Kiểm tra xem phiếu khảo sát có tồn tại không
        PhieuKhaoSat existingPhieuKhaoSat = phieuKhaoSatRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Phiếu khảo sát với ID: " + id + " không tồn tại"));

        // Cập nhật các trường cần thiết
        existingPhieuKhaoSat.setTenPhieuKhaoSat(phieuKhaoSat.getTenPhieuKhaoSat());
        existingPhieuKhaoSat.setNgayGioMo(phieuKhaoSat.getNgayGioMo());
        existingPhieuKhaoSat.setNgayGioDong(phieuKhaoSat.getNgayGioDong());

        // Lưu lại vào cơ sở dữ liệu
        return phieuKhaoSatRepository.save(existingPhieuKhaoSat);
    }

    /**
     * Xóa phiếu khảo sát theo ID.
     *
     * @param id Mã ID của phiếu khảo sát cần xóa.
     */
    public void deletePhieuKhaoSat(String id) {
        // Kiểm tra phiếu khảo sát có tồn tại không
        if (!phieuKhaoSatRepository.existsById(id)) {
            throw new IllegalArgumentException("Phiếu khảo sát với ID: " + id + " không tồn tại");
        }
        phieuKhaoSatRepository.deleteById(id);
    }

    /**
     * Lấy danh sách tất cả phiếu khảo sát.
     *
     * @return Danh sách phiếu khảo sát trong cơ sở dữ liệu.
     */
    public List<PhieuKhaoSat> getAllPhieuKhaoSat() {
        return phieuKhaoSatRepository.findAll();
    }

    /**
     * Lấy chi tiết phiếu khảo sát theo ID.
     *
     * @param id Mã ID của phiếu khảo sát.
     * @return Đối tượng Optional chứa phiếu khảo sát, nếu có.
     */
    public Optional<PhieuKhaoSat> getPhieuKhaoSatById(String id) {
        return phieuKhaoSatRepository.findById(id);
    }
}