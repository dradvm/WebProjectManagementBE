package com.WebProjectManagementBE.controller;


import com.WebProjectManagementBE.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TestController {

    @Autowired
    FirebaseService firebaseService;

    @PostMapping("/test/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        // Kiểm tra xem file có null không
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded.");
        }

        // In ra tên file nhận được (hoặc bạn có thể lưu file vào storage nếu muốn)
        String fileName = firebaseService.generateFileName(file);
        firebaseService.uploadFile(fileName, file);
        System.out.println("Received file: " + fileName);



        // Trả về thông báo thành công
        return ResponseEntity.ok("File uploaded successfully: " + fileName);
    }

}
