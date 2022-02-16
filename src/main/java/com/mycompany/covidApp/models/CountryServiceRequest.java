package com.mycompany.covidApp.models;

import java.util.List;

public class CountryServiceRequest {
    private String continent;
    private List<String> countryCodes;

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public List<String> getCountryCodes() {
        return countryCodes;
    }

    public void setCountryCodes(List<String> countryCodes) {
        this.countryCodes = countryCodes;
    }
}
