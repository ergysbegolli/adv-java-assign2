package com.mycompany.covidApp.faces.bean;

import com.mycompany.covidApp.core.controller.AppController;
import com.mycompany.covidApp.persistence.entities.Country;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class AddCountryBean  extends RequiredLoggedInBean {

    @ManagedProperty(value="#{listCountriesViewBean}")
    private ListCountriesViewBean listCountriesViewBean;

    private String code;
    private String name;
    private String continent;
    private double reproductionRate;
    private double population;
    private double stringencyIndex;
    private double medianAge;
    private boolean countryExists;

    public AddCountryBean() {

    }

    private AppController getAppController() {
        return AppController.getInstance();
    }

    private Country initCountry() {
        Country country = new Country
                .Builder(this.code)
                .location(this.name)
                .continent(this.continent)
                .population(this.population)
                .reproductionRate(this.reproductionRate)
                .stringencyIndex(this.stringencyIndex)
                .medianAge(this.medianAge)
                .build();

        return country;
    }

    public String create() {
        if (getAppController().getCountryByPK(this.code) != null) {
            setCountryExists(true);
            return "add-country";
        }
        else {
            setCountryExists(false);
            getAppController().addNewCountry(initCountry());

        listCountriesViewBean.fillCountries();
            return "country-list";
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    public double getReproductionRate() {
        return reproductionRate;
    }

    public void setReproductionRate(double reproductionRate) {
        this.reproductionRate = reproductionRate;
    }

    public double getStringencyIndex() {
        return stringencyIndex;
    }

    public void setStringencyIndex(double stringencyIndex) {
        this.stringencyIndex = stringencyIndex;
    }

    public double getMedianAge() {
        return medianAge;
    }

    public void setMedianAge(double medianAge) {
        this.medianAge = medianAge;
    }


    public void setListCountriesViewBean(ListCountriesViewBean listCountriesViewBean) {
        this.listCountriesViewBean = listCountriesViewBean;
    }

    public boolean isExistingCountry() {
        return countryExists;
    }

    public void setCountryExists(boolean countryExists) {
        this.countryExists = countryExists;
    }

}
