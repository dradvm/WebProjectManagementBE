/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.WebProjectManagementBE.model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "DuAn")
@jakarta.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DuAn.findAll", query = "SELECT d FROM DuAn d"),
    @NamedQuery(name = "DuAn.findByMaDuAn", query = "SELECT d FROM DuAn d WHERE d.maDuAn = :maDuAn"),
    @NamedQuery(name = "DuAn.findByTenDuAn", query = "SELECT d FROM DuAn d WHERE d.tenDuAn = :tenDuAn"),
    @NamedQuery(name = "DuAn.findByMoTa", query = "SELECT d FROM DuAn d WHERE d.moTa = :moTa"),
    @NamedQuery(name = "DuAn.findByNgayBatDau", query = "SELECT d FROM DuAn d WHERE d.ngayBatDau = :ngayBatDau"),
    @NamedQuery(name = "DuAn.findByNgayKetThuc", query = "SELECT d FROM DuAn d WHERE d.ngayKetThuc = :ngayKetThuc"),
    @NamedQuery(name = "DuAn.findByTrangThai", query = "SELECT d FROM DuAn d WHERE d.trangThai = :trangThai"),
    @NamedQuery(name = "DuAn.findByTienDoHoanThanh", query = "SELECT d FROM DuAn d WHERE d.tienDoHoanThanh = :tienDoHoanThanh")})
public class DuAn implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @jakarta.validation.constraints.Size(min = 1, max = 50)
    @Column(name = "maDuAn")
    private String maDuAn;
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @jakarta.validation.constraints.Size(min = 1, max = 255)
    @Column(name = "tenDuAn")
    private String tenDuAn;
    @jakarta.validation.constraints.Size(max = 2147483647)
    @Column(name = "moTa")
    private String moTa;
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @Column(name = "ngayBatDau")
    @Temporal(TemporalType.DATE)
    private Date ngayBatDau;
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @Column(name = "ngayKetThuc")
    @Temporal(TemporalType.DATE)
    private Date ngayKetThuc;
    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "trangThai")
    private String trangThai;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "tienDoHoanThanh")
    private Double tienDoHoanThanh;
    @JoinTable(name = "QuanLyDuAn", joinColumns = {
        @JoinColumn(name = "maDuAn", referencedColumnName = "maDuAn")}, inverseJoinColumns = {
        @JoinColumn(name = "maNguoiDung", referencedColumnName = "maNguoiDung")})
    @ManyToMany
    private Collection<NguoiDung> nguoiDungCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maDuAn")
    private Collection<PhieuKhaoSat> phieuKhaoSatCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maDuAn")
    private Collection<TapTin> tapTinCollection;

    public DuAn() {
    }

    public DuAn(String maDuAn) {
        this.maDuAn = maDuAn;
    }

    public DuAn(String maDuAn, String tenDuAn, Date ngayBatDau, Date ngayKetThuc) {
        this.maDuAn = maDuAn;
        this.tenDuAn = tenDuAn;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getMaDuAn() {
        return maDuAn;
    }

    public void setMaDuAn(String maDuAn) {
        this.maDuAn = maDuAn;
    }

    public String getTenDuAn() {
        return tenDuAn;
    }

    public void setTenDuAn(String tenDuAn) {
        this.tenDuAn = tenDuAn;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Double getTienDoHoanThanh() {
        return tienDoHoanThanh;
    }

    public void setTienDoHoanThanh(Double tienDoHoanThanh) {
        this.tienDoHoanThanh = tienDoHoanThanh;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public Collection<NguoiDung> getNguoiDungCollection() {
        return nguoiDungCollection;
    }

    public void setNguoiDungCollection(Collection<NguoiDung> nguoiDungCollection) {
        this.nguoiDungCollection = nguoiDungCollection;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public Collection<PhieuKhaoSat> getPhieuKhaoSatCollection() {
        return phieuKhaoSatCollection;
    }

    public void setPhieuKhaoSatCollection(Collection<PhieuKhaoSat> phieuKhaoSatCollection) {
        this.phieuKhaoSatCollection = phieuKhaoSatCollection;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public Collection<TapTin> getTapTinCollection() {
        return tapTinCollection;
    }

    public void setTapTinCollection(Collection<TapTin> tapTinCollection) {
        this.tapTinCollection = tapTinCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maDuAn != null ? maDuAn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DuAn)) {
            return false;
        }
        DuAn other = (DuAn) object;
        if ((this.maDuAn == null && other.maDuAn != null) || (this.maDuAn != null && !this.maDuAn.equals(other.maDuAn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.JavaWebProject.JavaWebProject.config.DuAn[ maDuAn=" + maDuAn + " ]";
    }
    
}
