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
@Table(name = "LoaiTapTin")
@jakarta.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoaiTapTin.findAll", query = "SELECT l FROM LoaiTapTin l"),
    @NamedQuery(name = "LoaiTapTin.findByMaLoai", query = "SELECT l FROM LoaiTapTin l WHERE l.maLoai = :maLoai"),
    @NamedQuery(name = "LoaiTapTin.findByLoai", query = "SELECT l FROM LoaiTapTin l WHERE l.loai = :loai")})
public class LoaiTapTin implements Serializable {

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
    @Column(name = "loai")
    private String loai;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maLoai")
    private Collection<TapTin> tapTinCollection;

    public LoaiTapTin() {
    }

    public LoaiTapTin(String maLoai) {
        this.maLoai = maLoai;
    }

    public LoaiTapTin(String maLoai, String loai) {
        this.maLoai = maLoai;
        this.loai = loai;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
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
        hash += (maLoai != null ? maLoai.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoaiTapTin)) {
            return false;
        }
        LoaiTapTin other = (LoaiTapTin) object;
        if ((this.maLoai == null && other.maLoai != null) || (this.maLoai != null && !this.maLoai.equals(other.maLoai))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.JavaWebProject.JavaWebProject.config.LoaiTapTin[ maLoai=" + maLoai + " ]";
    }
    
}
