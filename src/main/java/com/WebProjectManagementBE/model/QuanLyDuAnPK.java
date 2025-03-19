package com.WebProjectManagementBE.model;


import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

/**
 *
 * @author DUY
 */
@Embeddable
public class QuanLyDuAnPK implements Serializable {

    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @jakarta.validation.constraints.Size(min = 1, max = 50)
    @Column(name = "maNguoiDung")
    private String maNguoiDung;
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @jakarta.validation.constraints.Size(min = 1, max = 50)
    @Column(name = "maDuAn")
    private String maDuAn;

    public QuanLyDuAnPK() {
    }

    public QuanLyDuAnPK(String maNguoiDung, String maDuAn) {
        this.maNguoiDung = maNguoiDung;
        this.maDuAn = maDuAn;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getMaDuAn() {
        return maDuAn;
    }

    public void setMaDuAn(String maDuAn) {
        this.maDuAn = maDuAn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maNguoiDung != null ? maNguoiDung.hashCode() : 0);
        hash += (maDuAn != null ? maDuAn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuanLyDuAnPK)) {
            return false;
        }
        QuanLyDuAnPK other = (QuanLyDuAnPK) object;
        if ((this.maNguoiDung == null && other.maNguoiDung != null) || (this.maNguoiDung != null && !this.maNguoiDung.equals(other.maNguoiDung))) {
            return false;
        }
        if ((this.maDuAn == null && other.maDuAn != null) || (this.maDuAn != null && !this.maDuAn.equals(other.maDuAn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.JavaWebProject.JavaWebProject.controllers.QuanLyDuAnPK[ maNguoiDung=" + maNguoiDung + ", maDuAn=" + maDuAn + " ]";
    }

}
