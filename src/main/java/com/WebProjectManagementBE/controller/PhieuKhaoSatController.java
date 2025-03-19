package com.WebProjectManagementBE.controller;

import com.WebProjectManagementBE.model.PhieuKhaoSat;
import com.WebProjectManagementBE.service.PhieuKhaoSatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/phieu-khao-sat")
@CrossOrigin(origins = "*")
public class PhieuKhaoSatController {
    private final PhieuKhaoSatService phieuKhaoSatService;

    @Autowired
    public PhieuKhaoSatController(PhieuKhaoSatService phieuKhaoSatService) {
        this.phieuKhaoSatService = phieuKhaoSatService;
    }

    @GetMapping
    public ResponseEntity<List<PhieuKhaoSat>> getAllPhieuKhaoSat() {
        return ResponseEntity.ok(phieuKhaoSatService.getAllPhieuKhaoSat());
    }

    @GetMapping("/{maPhieuKhaoSat}")
    public ResponseEntity<PhieuKhaoSat> getPhieuKhaoSatByMa(@PathVariable String maPhieuKhaoSat) {
        return ResponseEntity.ok(phieuKhaoSatService.getPhieuKhaoSatById(maPhieuKhaoSat));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PhieuKhaoSat>> searchPhieuKhaoSat(@RequestParam String title) {
        return ResponseEntity.ok(phieuKhaoSatService.searchPhieuKhaoSat(title));
    }

    @PostMapping
    public ResponseEntity<PhieuKhaoSat> createPhieuKhaoSat(@RequestBody PhieuKhaoSat phieuKhaoSat) throws IOException {
        return ResponseEntity.ok(phieuKhaoSatService.createPhieuKhaoSat(phieuKhaoSat));
    }

    @PatchMapping("/{maPhieuKhaoSat}")
    public ResponseEntity<PhieuKhaoSat> updatePhieuKhaoSat(
            @PathVariable String maPhieuKhaoSat,
            @RequestBody PhieuKhaoSat phieuKhaoSatDetails) throws IOException {
        return ResponseEntity.ok(phieuKhaoSatService.updatePhieuKhaoSat(maPhieuKhaoSat, phieuKhaoSatDetails));
    }

    @DeleteMapping("/{maPhieuKhaoSat}")
    public ResponseEntity<Void> deletePhieuKhaoSat(@PathVariable String maPhieuKhaoSat) throws IOException {
        phieuKhaoSatService.deletePhieuKhaoSat(maPhieuKhaoSat);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{maPhieuKhaoSat}/ket-qua")
    public ResponseEntity<Object> getKetQuaKhaoSat(@PathVariable String maPhieuKhaoSat) throws IOException {
        return ResponseEntity.ok(phieuKhaoSatService.getKetQuaKhaoSat(maPhieuKhaoSat));
    }


    @GetMapping("/du-an/{maDuAn}")
    public ResponseEntity<List<PhieuKhaoSat>> getPhieuKhaoSatByMaDuAn(@PathVariable String maDuAn) {
        List<PhieuKhaoSat> phieuKhaoSatList = phieuKhaoSatService.getPhieuKhaoSatByMaDuAn(maDuAn);
        return ResponseEntity.ok(phieuKhaoSatList);
    }
}