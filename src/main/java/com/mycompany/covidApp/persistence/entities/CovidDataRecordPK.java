/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.covidApp.persistence.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Mysafir
 */
@Embeddable
public class CovidDataRecordPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "iso_code")
    private String isoCode;

    public CovidDataRecordPK() {
    }

    public CovidDataRecordPK(Date date, String isoCode) {
        this.date = date;
        this.isoCode = isoCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (date != null ? date.hashCode() : 0);
        hash += (isoCode != null ? isoCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CovidDataRecordPK)) {
            return false;
        }
        CovidDataRecordPK other = (CovidDataRecordPK) object;
        if ((this.date == null && other.date != null) || (this.date != null && !this.date.equals(other.date))) {
            return false;
        }
        if ((this.isoCode == null && other.isoCode != null) || (this.isoCode != null && !this.isoCode.equals(other.isoCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.covidApp.persistance.entities.CovidDataRecordPK[ date=" + date + ", isoCode=" + isoCode + " ]";
    }
    
}
