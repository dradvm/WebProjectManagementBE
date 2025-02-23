/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.WebProjectManagementBE.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author DUY
 */
@Entity
@Table(name = "TapTin")
@jakarta.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TapTin.findAll", query = "SELECT t FROM TapTin t"),
    @NamedQuery(name = "TapTin.findByMaTapTin", query = "SELECT t FROM TapTin t WHERE t.maTapTin = :maTapTin"),
    @NamedQuery(name = "TapTin.findByTenTapTin", query = "SELECT t FROM TapTin t WHERE t.tenTapTin = :tenTapTin"),
    @NamedQuery(name = "TapTin.findByLienKet", query = "SELECT t FROM TapTin t WHERE t.lienKet = :lienKet"),
    @NamedQuery(name = "TapTin.findByNgayTao", query = "SELECT t FROM TapTin t WHERE t.ngayTao = :ngayTao")})
public class TapTin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @jakarta.validation.constraints.Size(min = 1, max = 50)
    @Column(name = "maTapTin")
    private String maTapTin;
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @jakarta.validation.constraints.Size(min = 1, max = 255)
    @Column(name = "tenTapTin")
    private String tenTapTin;
    @jakarta.validation.constraints.Size(max = 2147483647)
    @Column(name = "lienKet")
    private String lienKet;
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @Column(name = "ngayTao")
    @Temporal(TemporalType.DATE)
    private Date ngayTao;
    @Lob
    @Column(name = "tapTin")
    private byte[] tapTin;
    @JoinColumn(name = "maDuAn", referencedColumnName = "maDuAn")
    @ManyToOne(optional = false)
    private DuAn maDuAn;
    @JoinColumn(name = "maLoai", referencedColumnName = "maLoai")
    @ManyToOne(optional = false)
    private LoaiTapTin maLoai;

    public TapTin() {
    }

    public TapTin(String maTapTin) {
        this.maTapTin = maTapTin;
    }

    public TapTin(String maTapTin, String tenTapTin, Date ngayTao) {
        this.maTapTin = maTapTin;
        this.tenTapTin = tenTapTin;
        this.ngayTao = ngayTao;
    }

    public String getMaTapTin() {
        return maTapTin;
    }

    public void setMaTapTin(String maTapTin) {
        this.maTapTin = maTapTin;
    }

    public String getTenTapTin() {
        return tenTapTin;
    }

    public void setTenTapTin(String tenTapTin) {
        this.tenTapTin = tenTapTin;
    }

    public String getLienKet() {
        return lienKet;
    }

    public void setLienKet(String lienKet) {
        this.lienKet = lienKet;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public byte[] getTapTin() {
        return tapTin;
    }

    public void setTapTin(byte[] tapTin) {
        this.tapTin = tapTin;
    }

    public DuAn getMaDuAn() {
        return maDuAn;
    }

    public void setMaDuAn(DuAn maDuAn) {
        this.maDuAn = maDuAn;
    }

    public LoaiTapTin getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(LoaiTapTin maLoai) {
        this.maLoai = maLoai;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maTapTin != null ? maTapTin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TapTin)) {
            return false;
        }
        TapTin other = (TapTin) object;
        if ((this.maTapTin == null && other.maTapTin != null) || (this.maTapTin != null && !this.maTapTin.equals(other.maTapTin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.JavaWebProject.JavaWebProject.config.TapTin[ maTapTin=" + maTapTin + " ]";
    }
    
}
