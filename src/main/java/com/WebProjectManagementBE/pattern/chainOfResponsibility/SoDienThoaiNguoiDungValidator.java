package com.WebProjectManagementBE.pattern.chainOfResponsibility;

import com.WebProjectManagementBE.DTO.NguoiDungRegisterDTO;
import com.WebProjectManagementBE.service.NguoiDungService;

import java.util.List;
import java.util.Map;

public class SoDienThoaiNguoiDungValidator extends NguoiDungValidator {
    private final NguoiDungService nguoiDungService;

    public SoDienThoaiNguoiDungValidator(NguoiDungService nguoiDungService) {
        this.nguoiDungService = nguoiDungService;
    }

    @Override
    protected boolean doValidate(NguoiDungRegisterDTO dto, List<Map<String, String>> errors) {
        if (nguoiDungService.isSoDienThoaiExist(dto.getSoDienThoai())) {
            errors.add(Map.of("field", "soDienThoai", "message", "Số điện thoại đã tồn tại"));
            return false; // Dừng nếu không hợp lệ
        }
        return true;
    }
}
