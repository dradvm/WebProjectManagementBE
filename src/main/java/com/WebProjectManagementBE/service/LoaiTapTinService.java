package com.WebProjectManagementBE.service;


import com.WebProjectManagementBE.model.LoaiTapTin;
import com.WebProjectManagementBE.repository.LoaiTapTinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class LoaiTapTinService {

    @Autowired
    LoaiTapTinRepository loaiTapTinRepository;

    public enum Loai {
        IMAGE("LTT1"),
        VIDEO("LTT2"),
        DOCUMENT("LTT3"),
        AUDIO("LTT4"),
        OTHERS("LTT5");

        private final String value;

        Loai(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    private static final List<String> IMAGE_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png", "gif", "bmp");
    private static final List<String> VIDEO_EXTENSIONS = Arrays.asList("mp4", "avi", "mov", "mkv");
    private static final List<String> DOCUMENT_EXTENSIONS = Arrays.asList("pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx");
    private static final List<String> AUDIO_EXTENSIONS = Arrays.asList("mp3", "wav", "aac", "flac");

    public Loai determineLoaiTapTin(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return Loai.OTHERS;
        }

        String filename = Objects.requireNonNull(file.getOriginalFilename()).toLowerCase();
        String extension = filename.contains(".") ? filename.substring(filename.lastIndexOf('.') + 1) : "";


        if (IMAGE_EXTENSIONS.contains(extension)) {
            return Loai.IMAGE;
        } else if (VIDEO_EXTENSIONS.contains(extension)) {
            return Loai.VIDEO;
        } else if (DOCUMENT_EXTENSIONS.contains(extension)) {
            return Loai.DOCUMENT;
        } else if (AUDIO_EXTENSIONS.contains(extension)) {
            return Loai.AUDIO;
        } else {
            return Loai.OTHERS;
        }
    }

    public LoaiTapTin getLoaiTapTin(Loai loai) {
        return loaiTapTinRepository.findById(loai.getValue()).orElseThrow();
    }

}
