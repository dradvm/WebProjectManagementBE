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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author DUY
 */
@Entity
@Table(name = "CauHoi")
@jakarta.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauHoi.findAll", query = "SELECT c FROM CauHoi c"),
    @NamedQuery(name = "CauHoi.findByMaCauHoi", query = "SELECT c FROM CauHoi c WHERE c.maCauHoi = :maCauHoi"),
    @NamedQuery(name = "CauHoi.findByCauHoi", query = "SELECT c FROM CauHoi c WHERE c.cauHoi = :cauHoi"),
    @NamedQuery(name = "CauHoi.findByBatBuoc", query = "SELECT c FROM CauHoi c WHERE c.batBuoc = :batBuoc"),
    @NamedQuery(name = "CauHoi.findByMoTa", query = "SELECT c FROM CauHoi c WHERE c.moTa = :moTa")})
public class CauHoi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @jakarta.validation.constraints.Size(min = 1, max = 50)
    @Column(name = "maCauHoi")
    private String maCauHoi;
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @jakarta.validation.constraints.Size(min = 1, max = 2147483647)
    @Column(name = "cauHoi")
    private String cauHoi;
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @Column(name = "batBuoc")
    private boolean batBuoc;
    @jakarta.validation.constraints.Size(max = 2147483647)
    @Column(name = "moTa")
    private String moTa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maCauHoi")
    private Collection<CauTraLoi> cauTraLoiCollection;
    @JoinColumn(name = "maLoai", referencedColumnName = "maLoai")
    @ManyToOne(optional = false)
    private LoaiCauHoi maLoai;
    @JoinColumn(name = "maPhieuKhaoSat", referencedColumnName = "maPhieuKhaoSat")
    @ManyToOne(optional = false)
    private PhieuKhaoSat maPhieuKhaoSat;

    public CauHoi() {
    }

    public CauHoi(String maCauHoi) {
        this.maCauHoi = maCauHoi;
    }

    public CauHoi(String maCauHoi, String cauHoi, boolean batBuoc) {
        this.maCauHoi = maCauHoi;
        this.cauHoi = cauHoi;
        this.batBuoc = batBuoc;
    }

    public String getMaCauHoi() {
        return maCauHoi;
    }

    public void setMaCauHoi(String maCauHoi) {
        this.maCauHoi = maCauHoi;
    }

    public String getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(String cauHoi) {
        this.cauHoi = cauHoi;
    }

    public boolean getBatBuoc() {
        return batBuoc;
    }

    public void setBatBuoc(boolean batBuoc) {
        this.batBuoc = batBuoc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public Collection<CauTraLoi> getCauTraLoiCollection() {
        return cauTraLoiCollection;
    }

    public void setCauTraLoiCollection(Collection<CauTraLoi> cauTraLoiCollection) {
        this.cauTraLoiCollection = cauTraLoiCollection;
    }

    public LoaiCauHoi getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(LoaiCauHoi maLoai) {
        this.maLoai = maLoai;
    }

    public PhieuKhaoSat getMaPhieuKhaoSat() {
        return maPhieuKhaoSat;
    }

    public void setMaPhieuKhaoSat(PhieuKhaoSat maPhieuKhaoSat) {
        this.maPhieuKhaoSat = maPhieuKhaoSat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maCauHoi != null ? maCauHoi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauHoi)) {
            return false;
        }
        CauHoi other = (CauHoi) object;
        if ((this.maCauHoi == null && other.maCauHoi != null) || (this.maCauHoi != null && !this.maCauHoi.equals(other.maCauHoi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.JavaWebProject.JavaWebProject.config.CauHoi[ maCauHoi=" + maCauHoi + " ]";
    }
    
}
