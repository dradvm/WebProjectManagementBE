package com.WebProjectManagementBE.pattern.chainOfResponsibility;

import com.WebProjectManagementBE.DTO.NguoiDungRegisterDTO;

import java.util.List;
import java.util.Map;

public abstract class NguoiDungValidator {
    protected NguoiDungValidator next;

    public NguoiDungValidator setNext(NguoiDungValidator next) {
        this.next = next;
        return next;
    }

    public void validate(NguoiDungRegisterDTO dto, List<Map<String, String>> errors) {
        doValidate(dto, errors);
        if (next != null) {
            next.validate(dto, errors);
        }
    }

    protected abstract boolean doValidate(NguoiDungRegisterDTO dto, List<Map<String, String>> errors);
}
