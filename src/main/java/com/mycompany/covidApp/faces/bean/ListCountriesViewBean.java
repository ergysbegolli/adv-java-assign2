package com.mycompany.covidApp.faces.bean;

import com.mycompany.covidApp.core.controller.AppController;
import com.mycompany.covidApp.persistence.entities.Country;


import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@SessionScoped
@ManagedBean
public class ListCountriesViewBean {
    private String filterName;
    private String filterContinent;

    private List<Country> countries;
    private List<String> sorts = new ArrayList<>();


    private AppController getAppController() {
        return AppController.getInstance();
    }

    public ListCountriesViewBean(){}

    @PostConstruct
    public void init() {
       fillCountries();
    }

    public void fillCountries() {
        this.countries = (List) getAppController().getAllCountries();
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getFilterContinent() {
        return filterContinent;
    }

    public void setFilterContinent(String filterContinent) {
        this.filterContinent = filterContinent;
    }

    public String filter() {
        countries = getAppController().filterCountries(filterName, filterContinent);
        return "";
    }

    public String clear() {
        fillCountries();
        filterName = null;
        filterContinent = null;
        return "";
    }

    public boolean renderClear() {
        return filterContinent != null || filterName != null;
    }

    public String sortCode() {
        return sort("isoCode");
    }

    public String sortName() {
        return sort("location");
    }

    public String sortContinent() {
        return sort("continent");
    }

    public String sortMedianAge() {
        return sort("medianAge");
    }

    public String sortPopulation() {
        return sort("population");
    }

    public String sortReproductionRate() {
        return sort("reproductionRate");
    }

    public String sortStringencyIndex() {
        return sort("stringencyIndex");
    }

    public String sort(String column) {
        if (sorts.contains(column)) {
            sorts.remove(column);
        } else {
            sorts.add(column);
        }

        if (sorts.isEmpty()) {
            filter();
        } else {
            countries = getAppController().sortCountries(filterContinent, filterName, sorts);
        }

        return "";
    }
}
