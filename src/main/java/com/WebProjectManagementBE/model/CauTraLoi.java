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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author DUY
 */
@Entity
@Table(name = "CauTraLoi")
@jakarta.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTraLoi.findAll", query = "SELECT c FROM CauTraLoi c"),
    @NamedQuery(name = "CauTraLoi.findByMaCauTraLoi", query = "SELECT c FROM CauTraLoi c WHERE c.maCauTraLoi = :maCauTraLoi"),
    @NamedQuery(name = "CauTraLoi.findByNoiDung", query = "SELECT c FROM CauTraLoi c WHERE c.noiDung = :noiDung")})
public class CauTraLoi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @jakarta.validation.constraints.Size(min = 1, max = 50)
    @Column(name = "maCauTraLoi")
    private String maCauTraLoi;
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @jakarta.validation.constraints.Size(min = 1, max = 2147483647)
    @Column(name = "noiDung")
    private String noiDung;
    @JoinColumn(name = "maCauHoi", referencedColumnName = "maCauHoi")
    @ManyToOne(optional = false)
    private CauHoi maCauHoi;
    @JoinColumn(name = "maNguoiDung", referencedColumnName = "maNguoiDung")
    @ManyToOne(optional = false)
    private NguoiDung maNguoiDung;

    public CauTraLoi() {
    }

    public CauTraLoi(String maCauTraLoi) {
        this.maCauTraLoi = maCauTraLoi;
    }

    public CauTraLoi(String maCauTraLoi, String noiDung) {
        this.maCauTraLoi = maCauTraLoi;
        this.noiDung = noiDung;
    }

    public String getMaCauTraLoi() {
        return maCauTraLoi;
    }

    public void setMaCauTraLoi(String maCauTraLoi) {
        this.maCauTraLoi = maCauTraLoi;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public CauHoi getMaCauHoi() {
        return maCauHoi;
    }

    public void setMaCauHoi(CauHoi maCauHoi) {
        this.maCauHoi = maCauHoi;
    }

    public NguoiDung getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(NguoiDung maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maCauTraLoi != null ? maCauTraLoi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTraLoi)) {
            return false;
        }
        CauTraLoi other = (CauTraLoi) object;
        if ((this.maCauTraLoi == null && other.maCauTraLoi != null) || (this.maCauTraLoi != null && !this.maCauTraLoi.equals(other.maCauTraLoi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.JavaWebProject.JavaWebProject.config.CauTraLoi[ maCauTraLoi=" + maCauTraLoi + " ]";
    }
    
}
