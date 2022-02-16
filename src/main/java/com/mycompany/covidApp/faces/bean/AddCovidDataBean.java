package com.mycompany.covidApp.faces.bean;

import com.mycompany.covidApp.core.controller.AppController;
import com.mycompany.covidApp.persistence.entities.CovidDataRecord;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.Date;


@ManagedBean
@RequestScoped
public class AddCovidDataBean extends RequiredLoggedInBean {

    @ManagedProperty(value="#{listCovidDataBean}")
    private ListCovidDataBean listCovidDataBean;

    private String code;
    private Date date;
    private double newTests;
    private double totalTests;
    private double newCases;
    private double totalCases;
    private double newDeaths;
    private double totalDeaths;
    private double newDeathsSmoothed;
    private double newCasesSmoothed;


    public AddCovidDataBean() {

    }

    private AppController getAppController() {
        return AppController.getInstance();
    }

    private CovidDataRecord initCovidData() {
        CovidDataRecord cr = new CovidDataRecord
                .Builder(this.date, this.code)
                .newCases(this.newCases)
                .newCasesSmoothed(this.newCasesSmoothed)
                .newDeaths(this.newDeaths)
                .newDeathsSmoothed(this.newDeathsSmoothed)
                .newTests(this.newTests)
                .totalCases(this.totalCases)
                .totalDeaths(this.totalDeaths)
                .totalTests(this.totalTests)
                .build();

        return cr;
    }

    public String create() {
            getAppController().addCovidDataRecord(initCovidData());

            listCovidDataBean.fillRecords();
            return "covid-data-list";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getNewTests() {
        return newTests;
    }

    public void setNewTests(double newTests) {
        this.newTests = newTests;
    }

    public double getTotalTests() {
        return totalTests;
    }

    public void setTotalTests(double totalTests) {
        this.totalTests = totalTests;
    }

    public double getNewCases() {
        return newCases;
    }

    public void setNewCases(double newCases) {
        this.newCases = newCases;
    }

    public double getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(double totalCases) {
        this.totalCases = totalCases;
    }

    public double getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(double newDeaths) {
        this.newDeaths = newDeaths;
    }

    public double getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(double totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public double getNewDeathsSmoothed() {
        return newDeathsSmoothed;
    }

    public void setNewDeathsSmoothed(double newDeathsSmoothed) {
        this.newDeathsSmoothed = newDeathsSmoothed;
    }

    public double getNewCasesSmoothed() {
        return newCasesSmoothed;
    }

    public void setNewCasesSmoothed(double newCasesSmoothed) {
        this.newCasesSmoothed = newCasesSmoothed;
    }

    public void setListCovidDataBean(ListCovidDataBean listCovidDataBean) {
        this.listCovidDataBean = listCovidDataBean;
    }
}
