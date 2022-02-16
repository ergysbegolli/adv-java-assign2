package com.mycompany.covidApp.faces.bean;

import com.mycompany.covidApp.core.controller.AppController;
import com.mycompany.covidApp.faces.FacesUtil;
import com.mycompany.covidApp.faces.sessionmanager.SessionManager;
import com.mycompany.covidApp.persistence.entities.Country;
import com.mycompany.covidApp.persistence.entities.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class ViewCountryBean extends RequiredLoggedInBean {
    private String countryCode;
    private Country country;

    @ManagedProperty(value="#{listCountriesViewBean}")
    private ListCountriesViewBean listCountriesViewBean;

    private AppController getAppController() {
        return AppController.getInstance();
    }


    public ViewCountryBean() {
    }

    public void init() {
        super.init();
        this.countryCode = FacesUtil.getRequestParameterValue("countryCode");
        country = getAppController().getCountryByPK(this.countryCode);
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String delete () {
        getAppController().deleteCoutnry(getAppController().getCountryByPK(this.countryCode));
        listCountriesViewBean.fillCountries();

        return FacesUtil.COUNTRY_LIST;
    }

    public void setListCountriesViewBean(ListCountriesViewBean listCountriesViewBean) {
        this.listCountriesViewBean = listCountriesViewBean;
    }

}
