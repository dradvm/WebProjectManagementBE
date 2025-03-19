/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.WebProjectManagementBE.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author DUY
 */
@Entity
@Table(name = "PhieuKhaoSat")
@jakarta.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PhieuKhaoSat.findAll", query = "SELECT p FROM PhieuKhaoSat p"),
    @NamedQuery(name = "PhieuKhaoSat.findByMaPhieuKhaoSat", query = "SELECT p FROM PhieuKhaoSat p WHERE p.maPhieuKhaoSat = :maPhieuKhaoSat"),
    @NamedQuery(name = "PhieuKhaoSat.findByTenPhieuKhaoSat", query = "SELECT p FROM PhieuKhaoSat p WHERE p.tenPhieuKhaoSat = :tenPhieuKhaoSat"),
    @NamedQuery(name = "PhieuKhaoSat.findByLienKet", query = "SELECT p FROM PhieuKhaoSat p WHERE p.lienKet = :lienKet"),
    @NamedQuery(name = "PhieuKhaoSat.findByLienKetTraLoi", query = "SELECT p FROM PhieuKhaoSat p WHERE p.lienKetTraLoi = :lienKetTraLoi"),
    @NamedQuery(name = "PhieuKhaoSat.findByNgayGioTao", query = "SELECT p FROM PhieuKhaoSat p WHERE p.ngayGioTao = :ngayGioTao"),
    @NamedQuery(name = "PhieuKhaoSat.findByNgayGioMo", query = "SELECT p FROM PhieuKhaoSat p WHERE p.ngayGioMo = :ngayGioMo"),
    @NamedQuery(name = "PhieuKhaoSat.findByNgayGioDong", query = "SELECT p FROM PhieuKhaoSat p WHERE p.ngayGioDong = :ngayGioDong")})
public class PhieuKhaoSat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @jakarta.validation.constraints.Size(min = 1, max = 50)
    @Column(name = "maPhieuKhaoSat")
    private String maPhieuKhaoSat;
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @jakarta.validation.constraints.Size(min = 1, max = 255)
    @Column(name = "tenPhieuKhaoSat")
    private String tenPhieuKhaoSat;
    @jakarta.validation.constraints.Size(max = 2147483647)
    @Column(name = "lienKet")
    private String lienKet;
    @Column(name = "lienKetTraLoi")
    private String lienKetTraLoi;
    @Column(name = "ngayGioTao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayGioTao;
    @Column(name = "ngayGioMo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayGioMo;
    @Column(name = "ngayGioDong")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayGioDong;
    @JoinColumn(name = "maDuAn", referencedColumnName = "maDuAn")
    @ManyToOne(optional = false)
    private DuAn maDuAn;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maPhieuKhaoSat")
    private Collection<CauHoi> cauHoiCollection;

    public PhieuKhaoSat() {
    }

    public PhieuKhaoSat(String maPhieuKhaoSat) {
        this.maPhieuKhaoSat = maPhieuKhaoSat;
    }

    public PhieuKhaoSat(String maPhieuKhaoSat, String tenPhieuKhaoSat) {
        this.maPhieuKhaoSat = maPhieuKhaoSat;
        this.tenPhieuKhaoSat = tenPhieuKhaoSat;
    }

    public String getMaPhieuKhaoSat() {
        return maPhieuKhaoSat;
    }

    public void setMaPhieuKhaoSat(String maPhieuKhaoSat) {
        this.maPhieuKhaoSat = maPhieuKhaoSat;
    }

    public String getTenPhieuKhaoSat() {
        return tenPhieuKhaoSat;
    }

    public void setTenPhieuKhaoSat(String tenPhieuKhaoSat) {
        this.tenPhieuKhaoSat = tenPhieuKhaoSat;
    }

    public String getLienKet() {
        return lienKet;
    }

    public void setLienKet(String lienKet) {
        this.lienKet = lienKet;
    }

    public String getLienKetTraLoi() { return lienKetTraLoi; }

    public void setLienKetTraLoi(String lienKetTraLoi) { this.lienKetTraLoi = lienKetTraLoi; }

    public Date getNgayGioTao() {
        return ngayGioTao;
    }

    public void setNgayGioTao(Date ngayGioTao) {
        this.ngayGioTao = ngayGioTao;
    }

    public Date getNgayGioMo() {
        return ngayGioMo;
    }

    public void setNgayGioMo(Date ngayGioMo) {
        this.ngayGioMo = ngayGioMo;
    }

    public Date getNgayGioDong() {
        return ngayGioDong;
    }

    public void setNgayGioDong(Date ngayGioDong) {
        this.ngayGioDong = ngayGioDong;
    }

    public DuAn getMaDuAn() {
        return maDuAn;
    }

    public void setMaDuAn(DuAn maDuAn) {
        this.maDuAn = maDuAn;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public Collection<CauHoi> getCauHoiCollection() {
        return cauHoiCollection;
    }

    public void setCauHoiCollection(Collection<CauHoi> cauHoiCollection) {
        this.cauHoiCollection = cauHoiCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maPhieuKhaoSat != null ? maPhieuKhaoSat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhieuKhaoSat)) {
            return false;
        }
        PhieuKhaoSat other = (PhieuKhaoSat) object;
        if ((this.maPhieuKhaoSat == null && other.maPhieuKhaoSat != null) || (this.maPhieuKhaoSat != null && !this.maPhieuKhaoSat.equals(other.maPhieuKhaoSat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.JavaWebProject.JavaWebProject.config.PhieuKhaoSat[ maPhieuKhaoSat=" + maPhieuKhaoSat + " ]";
    }
    
}
