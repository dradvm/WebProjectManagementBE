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
@Table(name = "LoaiCauHoi")
@jakarta.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoaiCauHoi.findAll", query = "SELECT l FROM LoaiCauHoi l"),
    @NamedQuery(name = "LoaiCauHoi.findByMaLoai", query = "SELECT l FROM LoaiCauHoi l WHERE l.maLoai = :maLoai"),
    @NamedQuery(name = "LoaiCauHoi.findByTenLoai", query = "SELECT l FROM LoaiCauHoi l WHERE l.tenLoai = :tenLoai"),
    @NamedQuery(name = "LoaiCauHoi.findByMoTa", query = "SELECT l FROM LoaiCauHoi l WHERE l.moTa = :moTa")})
public class LoaiCauHoi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @jakarta.validation.constraints.Size(min = 1, max = 50)
    @Column(name = "maLoai")
    private String maLoai;
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @jakarta.validation.constraints.Size(min = 1, max = 255)
    @Column(name = "tenLoai")
    private String tenLoai;
    @jakarta.validation.constraints.Size(max = 2147483647)
    @Column(name = "moTa")
    private String moTa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maLoai")
    private Collection<CauHoi> cauHoiCollection;

    public LoaiCauHoi() {
    }

    public LoaiCauHoi(String maLoai) {
        this.maLoai = maLoai;
    }

    public LoaiCauHoi(String maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
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
        hash += (maLoai != null ? maLoai.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoaiCauHoi)) {
            return false;
        }
        LoaiCauHoi other = (LoaiCauHoi) object;
        if ((this.maLoai == null && other.maLoai != null) || (this.maLoai != null && !this.maLoai.equals(other.maLoai))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.JavaWebProject.JavaWebProject.config.LoaiCauHoi[ maLoai=" + maLoai + " ]";
    }
    
}
