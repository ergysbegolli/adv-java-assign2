/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.covidApp.persistence.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Mysafir
 */
@Entity
@Table(name = "coviddata")
@NamedQueries({
    @NamedQuery(name = "CovidDataRecord.findAll", query = "SELECT c FROM CovidDataRecord c"),
        @NamedQuery(name = "CovidDataRecord.findByLatestDate", query="SELECT c FROM CovidDataRecord c WHERE c.covidDataRecordPK.date = (SELECT MAX(c.covidDataRecordPK.date) FROM CovidDataRecord c)"),
    @NamedQuery(name = "CovidDataRecord.findByDate", query = "SELECT c FROM CovidDataRecord c WHERE c.covidDataRecordPK.date = :date"),
    @NamedQuery(name = "CovidDataRecord.findByIsoCode", query = "SELECT c FROM CovidDataRecord c WHERE c.covidDataRecordPK.isoCode = :isoCode"),
    @NamedQuery(name = "CovidDataRecord.findByTotalCases", query = "SELECT c FROM CovidDataRecord c WHERE c.totalCases = :totalCases"),
    @NamedQuery(name = "CovidDataRecord.findByNewCases", query = "SELECT c FROM CovidDataRecord c WHERE c.newCases = :newCases"),
    @NamedQuery(name = "CovidDataRecord.findByNewCasesSmoothed", query = "SELECT c FROM CovidDataRecord c WHERE c.newCasesSmoothed = :newCasesSmoothed"),
    @NamedQuery(name = "CovidDataRecord.findByTotalDeaths", query = "SELECT c FROM CovidDataRecord c WHERE c.totalDeaths = :totalDeaths"),
    @NamedQuery(name = "CovidDataRecord.findByNewDeaths", query = "SELECT c FROM CovidDataRecord c WHERE c.newDeaths = :newDeaths"),
    @NamedQuery(name = "CovidDataRecord.findByNewDeathsSmoothed", query = "SELECT c FROM CovidDataRecord c WHERE c.newDeathsSmoothed = :newDeathsSmoothed"),
    @NamedQuery(name = "CovidDataRecord.findByTotalTests", query = "SELECT c FROM CovidDataRecord c WHERE c.totalTests = :totalTests"),
    @NamedQuery(name = "CovidDataRecord.findByNewTests", query = "SELECT c FROM CovidDataRecord c WHERE c.newTests = :newTests"),
    @NamedQuery(name = "CovidDataRecord.deleteCovidDataByIsoCode", query = "DELETE FROM CovidDataRecord c WHERE c.covidDataRecordPK.isoCode = :isoCode")})
public class CovidDataRecord implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CovidDataRecordPK covidDataRecordPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_cases")
    private Double totalCases;
    @Column(name = "new_cases")
    private Double newCases;
    @Column(name = "new_cases_smoothed")
    private Double newCasesSmoothed;
    @Column(name = "total_deaths")
    private Double totalDeaths;
    @Column(name = "new_deaths")
    private Double newDeaths;
    @Column(name = "new_deaths_smoothed")
    private Double newDeathsSmoothed;
    @Column(name = "total_tests")
    private Double totalTests;
    @Column(name = "new_tests")
    private Double newTests;

    public CovidDataRecord() {
    }

    public CovidDataRecord(CovidDataRecordPK covidDataRecordPK) {
        this.covidDataRecordPK = covidDataRecordPK;
    }

    public CovidDataRecord(Date date, String isoCode) {
        this.covidDataRecordPK = new CovidDataRecordPK(date, isoCode);
    }

    public CovidDataRecord(Builder b) {
        this(b.date, b.isoCode);
        this.newCases = b.newCases;
        this.totalCases = b.totalCases;
        this.newCasesSmoothed = b.newCasesSmoothed;
        this.newDeaths = b.newDeaths;
        this.newDeathsSmoothed = b.newDeathsSmoothed;
        this.totalDeaths = b.totalDeaths;
        this.newTests = b.newTests;
        this.totalTests = b.totalTests;
    }

    public CovidDataRecordPK getCovidDataRecordPK() {
        return covidDataRecordPK;
    }

    public void setCovidDataRecordPK(CovidDataRecordPK covidDataRecordPK) {
        this.covidDataRecordPK = covidDataRecordPK;
    }

    public Double getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(Double totalCases) {
        this.totalCases = totalCases;
    }

    public Double getNewCases() {
        return newCases;
    }

    public void setNewCases(Double newCases) {
        this.newCases = newCases;
    }

    public Double getNewCasesSmoothed() {
        return newCasesSmoothed;
    }

    public void setNewCasesSmoothed(Double newCasesSmoothed) {
        this.newCasesSmoothed = newCasesSmoothed;
    }

    public Double getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(Double totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public Double getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(Double newDeaths) {
        this.newDeaths = newDeaths;
    }

    public Double getNewDeathsSmoothed() {
        return newDeathsSmoothed;
    }

    public void setNewDeathsSmoothed(Double newDeathsSmoothed) {
        this.newDeathsSmoothed = newDeathsSmoothed;
    }

    public Double getTotalTests() {
        return totalTests;
    }

    public void setTotalTests(Double totalTests) {
        this.totalTests = totalTests;
    }

    public Double getNewTests() {
        return newTests;
    }

    public void setNewTests(Double newTests) {
        this.newTests = newTests;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (covidDataRecordPK != null ? covidDataRecordPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CovidDataRecord)) {
            return false;
        }
        CovidDataRecord other = (CovidDataRecord) object;
        if ((this.covidDataRecordPK == null && other.covidDataRecordPK != null) || (this.covidDataRecordPK != null && !this.covidDataRecordPK.equals(other.covidDataRecordPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.covidApp.persistance.entities.CovidDataRecord[ covidDataRecordPK=" + covidDataRecordPK + " ]";
    }

    public static class Builder {

        private String isoCode;
        private Date date;
        private double newCases;
        private double totalCases;
        private double newCasesSmoothed;
        private double newDeaths;
        private double newDeathsSmoothed;
        private double totalDeaths;
        private double newTests;
        private double totalTests;

        public Builder(Date date, String isoCode) {
            this.isoCode = isoCode;
            this.date = date;
        }

        public Builder newCases(double newCases) {
            this.newCases = newCases;
            return this;
        }

        public Builder totalCases(double totalCases) {
            this.totalCases = totalCases;
            return this;
        }

        public Builder newCasesSmoothed(double newCasesSmoothed) {
            this.newCasesSmoothed = newCasesSmoothed;
            return this;
        }

        public Builder newDeaths(double newDeaths) {
            this.newDeaths = newDeaths;
            return this;
        }

        public Builder newDeathsSmoothed(double newDeathsSmoothed) {
            this.newDeathsSmoothed = newDeathsSmoothed;
            return this;
        }

        public Builder totalDeaths(double totalDeaths) {
            this.totalDeaths = totalDeaths;
            return this;
        }

        public Builder newTests(double newTests) {
            this.newTests = newTests;
            return this;
        }

        public Builder totalTests(double totalTests) {
            this.totalTests = totalTests;
            return this;
        }

        public CovidDataRecord build() {
            return new CovidDataRecord(this);
        }
    }

}
