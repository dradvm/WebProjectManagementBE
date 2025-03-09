package com.WebProjectManagementBE.pattern.chainOfResponsibility;

import com.WebProjectManagementBE.DTO.NguoiDungRegisterDTO;
import com.WebProjectManagementBE.service.NguoiDungService;

import java.util.List;
import java.util.Map;

public class EmailNguoiDungValidator extends NguoiDungValidator {
    private final NguoiDungService nguoiDungService;

    public EmailNguoiDungValidator(NguoiDungService nguoiDungService) {
        this.nguoiDungService = nguoiDungService;
    }

    @Override
    protected boolean doValidate(NguoiDungRegisterDTO dto, List<Map<String, String>> errors) {
        if (nguoiDungService.isEmailExist(dto.getEmail())) {
            errors.add(Map.of("field", "email", "message", "Email đã tồn tại"));
            return false;
        }
        return true;
    }
}
