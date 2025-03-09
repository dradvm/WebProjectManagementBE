package com.WebProjectManagementBE.service;

import com.WebProjectManagementBE.model.Quyen;
import com.WebProjectManagementBE.repository.QuyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuyenService {
    @Autowired
    private QuyenRepository quyenRepository;


    public Quyen getQuyenNguoiDung() {
        return quyenRepository.getReferenceById("Q1");
    }

    public Quyen getQuyenQuanTriVien() {
        return quyenRepository.getReferenceById("Q2");
    }

}
