/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.covidApp.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Mysafir
 */
@Entity
@Table(name = "countries")
@NamedQueries({
    @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c"),
    @NamedQuery(name = "Country.findByIsoCode", query = "SELECT c FROM Country c WHERE c.isoCode = :isoCode"),
    @NamedQuery(name = "Country.findByLocation", query = "SELECT c FROM Country c WHERE c.location = :location"),
        @NamedQuery(name="Country.findByNamePattern", query = "SELECT c FROM Country c WHERE LOWER(c.location) LIKE LOWER(:name)"),
    @NamedQuery(name = "Country.findByContinent", query = "SELECT c FROM Country c WHERE c.continent = :continent"),
    @NamedQuery(name = "Country.findByReproductionRate", query = "SELECT c FROM Country c WHERE c.reproductionRate = :reproductionRate"),
    @NamedQuery(name = "Country.findByStringencyIndex", query = "SELECT c FROM Country c WHERE c.stringencyIndex = :stringencyIndex"),
    @NamedQuery(name = "Country.findByPopulation", query = "SELECT c FROM Country c WHERE c.population = :population"),
    @NamedQuery(name = "Country.findByMedianAge", query = "SELECT c FROM Country c WHERE c.medianAge = :medianAge"),
        @NamedQuery(name = "Country.findByContinentAndNamePattern", query = "SELECT c FROM Country c WHERE c.continent = :continent AND LOWER(c.location) LIKE LOWER(:name)")
})
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "iso_code")
    private String isoCode;
    @Size(max = 255)
    @Column(name = "location")
    private String location;
    @Size(max = 255)
    @Column(name = "continent")
    private String continent;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "reproduction_rate")
    private Double reproductionRate;
    @Column(name = "stringency_index")
    private Double stringencyIndex;
    @Column(name = "population")
    private Double population;
    @Column(name = "median_age")
    private Double medianAge;

    public Country() {
    }

    public Country(String isoCode) {
        this.isoCode = isoCode;
    }

    public Country(Builder b) {
        this.isoCode = b.isoCode;
        this.location = b.location;
        this.continent = b.continent;
        this.population = b.population;
        this.medianAge = b.medianAge;
        this.reproductionRate = b.reproductionRate;
        this.stringencyIndex = b.stringencyIndex;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Double getReproductionRate() {
        return reproductionRate;
    }

    public void setReproductionRate(Double reproductionRate) {
        this.reproductionRate = reproductionRate;
    }

    public Double getStringencyIndex() {
        return stringencyIndex;
    }

    public void setStringencyIndex(Double stringencyIndex) {
        this.stringencyIndex = stringencyIndex;
    }

    public Double getPopulation() {
        return population;
    }

    public void setPopulation(Double population) {
        this.population = population;
    }

    public Double getMedianAge() {
        return medianAge;
    }

    public void setMedianAge(Double medianAge) {
        this.medianAge = medianAge;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (isoCode != null ? isoCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.isoCode == null && other.isoCode != null) || (this.isoCode != null && !this.isoCode.equals(other.isoCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.covidApp.persistance.entities.Country[ isoCode=" + isoCode + " ]";
    }

    public static class Builder {

        private String isoCode;
        private String location;
        private String continent;
        private Double reproductionRate;
        private Double stringencyIndex;
        private Double population;
        private Double medianAge;

        public Builder(String isoCode) {
            this.isoCode = isoCode;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder continent(String continent) {
            this.continent = continent;
            return this;
        }

        public Builder reproductionRate(double reproductionRate) {
            this.reproductionRate = reproductionRate;
            return this;
        }

        public Builder stringencyIndex(double stringencyIndex) {
            this.stringencyIndex = stringencyIndex;
            return this;
        }

        public Builder population(double population) {
            this.population = population;
            return this;
        }

        public Builder medianAge(double medianAge) {
            this.medianAge = medianAge;
            return this;
        }

        public Country build() {
            return new Country(this);
        }

    }
}
