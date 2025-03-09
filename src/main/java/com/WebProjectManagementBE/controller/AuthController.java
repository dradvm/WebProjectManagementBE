package com.WebProjectManagementBE.controller;

import com.WebProjectManagementBE.DTO.AuthRequestDTO;
import com.WebProjectManagementBE.DTO.NguoiDungRegisterDTO;
import com.WebProjectManagementBE.model.NguoiDung;
import com.WebProjectManagementBE.pattern.factory.NguoiDungFactory;
import com.WebProjectManagementBE.pattern.chainOfResponsibility.EmailNguoiDungValidator;
import com.WebProjectManagementBE.pattern.chainOfResponsibility.NguoiDungValidator;
import com.WebProjectManagementBE.pattern.chainOfResponsibility.SoDienThoaiNguoiDungValidator;
import com.WebProjectManagementBE.service.JWTService;
import com.WebProjectManagementBE.service.NguoiDungService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AuthController {


    @Autowired
    private NguoiDungFactory nguoiDungFactory;

    @Autowired
    private NguoiDungService nguoiDungService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register/nguoidung")
    public ResponseEntity<?> registerNguoiDung(@RequestBody NguoiDungRegisterDTO nguoiDungRegisterDTO) {
        Map<String, String> response = new HashMap<>();
        List<Map<String, String>> errors = new ArrayList<Map<String, String>>();

        NguoiDungValidator validator = new SoDienThoaiNguoiDungValidator(nguoiDungService);
        validator.setNext(new EmailNguoiDungValidator(nguoiDungService));
        validator.validate(nguoiDungRegisterDTO, errors);
        if (!errors.isEmpty()) {
            return ResponseEntity.internalServerError().body(errors);
        }

        NguoiDung nguoiDung = nguoiDungFactory.createNguoiDung(nguoiDungRegisterDTO);
        nguoiDungService.registerNguoiDung(nguoiDung);

        response.put("token", jwtService.generateToken(nguoiDung.getMaNguoiDung()));
        response.put("message", "Thành công");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/quantrivien")
    public ResponseEntity<?> registerQuanTriVien(@RequestBody NguoiDungRegisterDTO nguoiDungRegisterDTO) {
        Map<String, String> response = new HashMap<>();
        List<Map<String, String>> errors = new ArrayList<Map<String, String>>();

        NguoiDungValidator validator = new SoDienThoaiNguoiDungValidator(nguoiDungService);
        validator.setNext(new EmailNguoiDungValidator(nguoiDungService));
        validator.validate(nguoiDungRegisterDTO, errors);
        if (!errors.isEmpty()) {
            return ResponseEntity.internalServerError().body(errors);
        }

        NguoiDung nguoiDung = nguoiDungFactory.createQuanTriVien(nguoiDungRegisterDTO);
        nguoiDungService.registerNguoiDung(nguoiDung);

        response.put("token", jwtService.generateToken(nguoiDung.getMaNguoiDung()));
        response.put("message", "Thành công");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequestDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getMatKhau()));
        String token =  jwtService.generateToken(nguoiDungService.findByEmail(authRequestDTO.getEmail()).getMaNguoiDung());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("OK");
    }
 }
