/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.WebProjectManagementBE.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
@Table(name = "NguoiDung")
@jakarta.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NguoiDung.findAll", query = "SELECT n FROM NguoiDung n"),
    @NamedQuery(name = "NguoiDung.findByMaNguoiDung", query = "SELECT n FROM NguoiDung n WHERE n.maNguoiDung = :maNguoiDung"),
    @NamedQuery(name = "NguoiDung.findByHoTen", query = "SELECT n FROM NguoiDung n WHERE n.hoTen = :hoTen"),
    @NamedQuery(name = "NguoiDung.findByLaNam", query = "SELECT n FROM NguoiDung n WHERE n.laNam = :laNam"),
    @NamedQuery(name = "NguoiDung.findBySoDienThoai", query = "SELECT n FROM NguoiDung n WHERE n.soDienThoai = :soDienThoai"),
    @NamedQuery(name = "NguoiDung.findByEmail", query = "SELECT n FROM NguoiDung n WHERE n.email = :email"),
    @NamedQuery(name = "NguoiDung.findByMatKhau", query = "SELECT n FROM NguoiDung n WHERE n.matKhau = :matKhau")})
public class NguoiDung implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @jakarta.validation.constraints.Size(min = 1, max = 50)
    @Column(name = "maNguoiDung")
    private String maNguoiDung;
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @jakarta.validation.constraints.Size(min = 1, max = 255)
    @Column(name = "hoTen")
    private String hoTen;
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @Column(name = "laNam")
    private boolean laNam;
    @jakarta.validation.constraints.Size(max = 15)
    @Column(name = "soDienThoai")
    private String soDienThoai;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @jakarta.validation.constraints.Size(max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @jakarta.validation.constraints.Size(min = 1, max = 255)
    @Column(name = "matKhau")
    private String matKhau;
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @Column(name = "active")
    private boolean active = true;
    @ManyToMany(mappedBy = "nguoiDungCollection")
    private Collection<DuAn> duAnCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maNguoiDung")
    private Collection<CauTraLoi> cauTraLoiCollection;
    @JoinColumn(name = "maQuyen", referencedColumnName = "maQuyen")
    @ManyToOne(optional = false)
    private Quyen maQuyen;

    public NguoiDung() {
    }

    public NguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public NguoiDung(String maNguoiDung, String hoTen, boolean laNam, String matKhau) {
        this.maNguoiDung = maNguoiDung;
        this.hoTen = hoTen;
        this.laNam = laNam;
        this.matKhau = matKhau;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public boolean getLaNam() {
        return laNam;
    }

    public void setLaNam(boolean laNam) {
        this.laNam = laNam;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public boolean getActive() { return active; }

    public void setActive(boolean active) {this.active = active; }

    @jakarta.xml.bind.annotation.XmlTransient
    public Collection<DuAn> getDuAnCollection() {
        return duAnCollection;
    }

    public void setDuAnCollection(Collection<DuAn> duAnCollection) {
        this.duAnCollection = duAnCollection;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public Collection<CauTraLoi> getCauTraLoiCollection() {
        return cauTraLoiCollection;
    }

    public void setCauTraLoiCollection(Collection<CauTraLoi> cauTraLoiCollection) {
        this.cauTraLoiCollection = cauTraLoiCollection;
    }

    public Quyen getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(Quyen maQuyen) {
        this.maQuyen = maQuyen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maNguoiDung != null ? maNguoiDung.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NguoiDung)) {
            return false;
        }
        NguoiDung other = (NguoiDung) object;
        if ((this.maNguoiDung == null && other.maNguoiDung != null) || (this.maNguoiDung != null && !this.maNguoiDung.equals(other.maNguoiDung))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.JavaWebProject.JavaWebProject.config.NguoiDung[ maNguoiDung=" + maNguoiDung + " ]";
    }
    
}
