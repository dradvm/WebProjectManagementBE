package com.WebProjectManagementBE.pattern.templateMethod;

import com.WebProjectManagementBE.model.LoaiTapTin;
import com.WebProjectManagementBE.service.FirebaseService;
import com.WebProjectManagementBE.service.LoaiTapTinService;
import com.WebProjectManagementBE.service.TapTinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class DefaultFileUploader extends AbstractFileUploader {

    @Autowired
    FirebaseService firebaseService;

    @Autowired
    TapTinService tapTinService;

    @Override
    protected void saveFile(String filename, MultipartFile file) {
        firebaseService.uploadFile(filename, file);
    }

    @Override
    protected void postProcess(String maDuAn, String filename, MultipartFile file) {
        tapTinService.createTapTin(maDuAn, filename, file);
    }
}
