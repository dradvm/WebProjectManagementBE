/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.WebProjectManagementBE.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "Quyen")
@jakarta.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Quyen.findAll", query = "SELECT q FROM Quyen q"),
    @NamedQuery(name = "Quyen.findByMaQuyen", query = "SELECT q FROM Quyen q WHERE q.maQuyen = :maQuyen"),
    @NamedQuery(name = "Quyen.findByTenQuyen", query = "SELECT q FROM Quyen q WHERE q.tenQuyen = :tenQuyen")})
public class Quyen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @jakarta.validation.constraints.Size(min = 1, max = 50)
    @Column(name = "maQuyen")
    @JsonProperty("maQuyen")
    private String maQuyen;
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @jakarta.validation.constraints.Size(min = 1, max = 255)
    @Column(name = "tenQuyen")
    private String tenQuyen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maQuyen")
    private Collection<NguoiDung> nguoiDungCollection;

    public Quyen() {
    }

    public Quyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }

    public Quyen(String maQuyen, String tenQuyen) {
        this.maQuyen = maQuyen;
        this.tenQuyen = tenQuyen;
    }

    public String getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }

    public String getTenQuyen() {
        return tenQuyen;
    }

    public void setTenQuyen(String tenQuyen) {
        this.tenQuyen = tenQuyen;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public Collection<NguoiDung> getNguoiDungCollection() {
        return nguoiDungCollection;
    }

    public void setNguoiDungCollection(Collection<NguoiDung> nguoiDungCollection) {
        this.nguoiDungCollection = nguoiDungCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maQuyen != null ? maQuyen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quyen)) {
            return false;
        }
        Quyen other = (Quyen) object;
        if ((this.maQuyen == null && other.maQuyen != null) || (this.maQuyen != null && !this.maQuyen.equals(other.maQuyen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.JavaWebProject.JavaWebProject.config.Quyen[ maQuyen=" + maQuyen + " ]";
    }
    
}
