package com.WebProjectManagementBE.pattern.templateMethod;

import org.springframework.web.multipart.MultipartFile;

public abstract class AbstractFileUploader {
    public final void uploadFile(String maDuAn, String filename,MultipartFile file) {
        validateFile(filename, file);
        saveFile(filename, file);
        postProcess(maDuAn, filename, file);
    }

    protected void validateFile(String filename,MultipartFile file) {

    }

    protected abstract void saveFile(String filename,MultipartFile file);

    protected void postProcess(String maDuAn, String filename,MultipartFile file) {

    }
}

