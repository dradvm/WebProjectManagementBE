package com.WebProjectManagementBE.model;


import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
@Table(name = "QuanLyDuAn")
@jakarta.xml.bind.annotation.XmlRootElement
@NamedQueries({
        @NamedQuery(name = "QuanLyDuAn.findAll", query = "SELECT q FROM QuanLyDuAn q"),
        @NamedQuery(name = "QuanLyDuAn.findByMaNguoiDung", query = "SELECT q FROM QuanLyDuAn q WHERE q.quanLyDuAnPK.maNguoiDung = :maNguoiDung"),
        @NamedQuery(name = "QuanLyDuAn.findByMaDuAn", query = "SELECT q FROM QuanLyDuAn q WHERE q.quanLyDuAnPK.maDuAn = :maDuAn"),
        @NamedQuery(name = "QuanLyDuAn.findByChuDuAn", query = "SELECT q FROM QuanLyDuAn q WHERE q.chuDuAn = :chuDuAn")})
public class QuanLyDuAn implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected QuanLyDuAnPK quanLyDuAnPK;
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @Column(name = "chuDuAn")
    private boolean chuDuAn;
    @JoinColumn(name = "maDuAn", referencedColumnName = "maDuAn", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DuAn duAn;
    @JoinColumn(name = "maNguoiDung", referencedColumnName = "maNguoiDung", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private NguoiDung nguoiDung;

    public QuanLyDuAn() {
    }

    public QuanLyDuAn(QuanLyDuAnPK quanLyDuAnPK) {
        this.quanLyDuAnPK = quanLyDuAnPK;
    }

    public QuanLyDuAn(QuanLyDuAnPK quanLyDuAnPK, boolean chuDuAn) {
        this.quanLyDuAnPK = quanLyDuAnPK;
        this.chuDuAn = chuDuAn;
    }

    public QuanLyDuAn(String maNguoiDung, String maDuAn) {
        this.quanLyDuAnPK = new QuanLyDuAnPK(maNguoiDung, maDuAn);
    }

    public QuanLyDuAnPK getQuanLyDuAnPK() {
        return quanLyDuAnPK;
    }

    public void setQuanLyDuAnPK(QuanLyDuAnPK quanLyDuAnPK) {
        this.quanLyDuAnPK = quanLyDuAnPK;
    }

    public boolean getChuDuAn() {
        return chuDuAn;
    }

    public void setChuDuAn(boolean chuDuAn) {
        this.chuDuAn = chuDuAn;
    }

    public DuAn getDuAn() {
        return duAn;
    }

    public void setDuAn(DuAn duAn) {
        this.duAn = duAn;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (quanLyDuAnPK != null ? quanLyDuAnPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuanLyDuAn)) {
            return false;
        }
        QuanLyDuAn other = (QuanLyDuAn) object;
        if ((this.quanLyDuAnPK == null && other.quanLyDuAnPK != null) || (this.quanLyDuAnPK != null && !this.quanLyDuAnPK.equals(other.quanLyDuAnPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.JavaWebProject.JavaWebProject.controllers.QuanLyDuAn[ quanLyDuAnPK=" + quanLyDuAnPK + " ]";
    }

}
