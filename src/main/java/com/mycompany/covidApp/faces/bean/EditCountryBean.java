package com.mycompany.covidApp.faces.bean;

import com.mycompany.covidApp.core.controller.AppController;
import com.mycompany.covidApp.faces.FacesUtil;
import com.mycompany.covidApp.faces.sessionmanager.SessionManager;
import com.mycompany.covidApp.persistence.entities.Country;
import com.mycompany.covidApp.persistence.entities.User;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class EditCountryBean  extends RequiredLoggedInBean {
    private String countryCode;
    private String continent;
    private String name;
    private double reproductionRate;
    private double population;
    private double stringencyIndex;
    private double medianAge;

    @ManagedProperty(value="#{listCountriesViewBean}")
    private ListCountriesViewBean listCountriesViewBean;

    public EditCountryBean() {
    }

    private AppController getAppController() {
        return AppController.getInstance();
    }

    @PostConstruct
    public void init() {
        super.init();
        this.countryCode = FacesUtil.getRequestParameterValue("countryCode");
        Country country = getAppController().getCountryByPK(this.countryCode);
        populateBeanFromModel(country);

    }

    private void populateBeanFromModel(Country country){
        countryCode = country.getIsoCode();
        continent = country.getContinent();
        name = country.getLocation();
        reproductionRate = country.getReproductionRate();
        stringencyIndex = country.getStringencyIndex();
        population = country.getPopulation();
        medianAge = country.getMedianAge();
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getReproductionRate() {
        return reproductionRate;
    }

    public void setReproductionRate(double reproductionRate) {
        this.reproductionRate = reproductionRate;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
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

    private void populateModelFromBean(Country country) {
        country.setPopulation(this.population);
        country.setLocation(this.name);
        country.setContinent(this.continent);
        country.setMedianAge(this.medianAge);
        country.setReproductionRate(this.reproductionRate);
        country.setStringencyIndex(this.stringencyIndex);
        country.setPopulation(this.population);
    }

    public String update() {
        Country country = getAppController().getCountryByPK(this.countryCode);
        populateModelFromBean(country);

            getAppController().updateCountry(country);
             listCountriesViewBean.fillCountries();

        return FacesUtil.COUNTRY_LIST;
    }

    public void setListCountriesViewBean(ListCountriesViewBean listCountriesViewBean) {
        this.listCountriesViewBean = listCountriesViewBean;
    }
}
