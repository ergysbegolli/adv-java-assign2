package com.mycompany.covidApp.faces.bean;

import com.mycompany.covidApp.core.controller.AppController;
import com.mycompany.covidApp.faces.FacesUtil;
import com.mycompany.covidApp.persistence.entities.Country;
import com.mycompany.covidApp.persistence.entities.CovidDataRecord;
import com.mycompany.covidApp.persistence.entities.CovidDataRecordPK;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.Date;

import static com.mycompany.covidApp.utils.Utilities.StringFromDate;
import static com.mycompany.covidApp.utils.Utilities.dateFromString;


@ManagedBean
@RequestScoped
public class EditCovidDataBean extends RequiredLoggedInBean {

    @ManagedProperty(value="#{listCovidDataBean}")
    private ListCovidDataBean listCovidDataBean;

    private String countryCode;
    private Date date;
    private double newTests;
    private double totalTests;
    private double newCases;
    private double totalCases;
    private double newDeaths;
    private double totalDeaths;
    private double newDeathsSmoothed;
    private double newCasesSmoothed;

    public EditCovidDataBean() {
    }

    private AppController getAppController() {
        return AppController.getInstance();
    }

    public void init() {
        super.init();
        this.countryCode = FacesUtil.getRequestParameterValue("countryCode");
        this.date = dateFromString(FacesUtil.getRequestParameterValue("date"));
      CovidDataRecord cd = getAppController().getCovidDataByPK(new CovidDataRecordPK(this.date, this.countryCode));
   this.newCases = cd.getNewCases();
   this.newCasesSmoothed = cd.getNewCasesSmoothed();
   this.newDeaths = cd.getNewDeaths();
   this.newDeathsSmoothed = cd.getNewDeathsSmoothed();
   this.totalCases = cd.getTotalCases();
   this.totalDeaths = cd.getTotalDeaths();
   this.totalTests = cd.getTotalTests();
   this.newTests = cd.getNewTests();
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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

    public String update() {
        CovidDataRecord cd =new CovidDataRecord
                .Builder(this.date, this.countryCode)
                .newCases(this.newCases)
                .newCasesSmoothed(this.newCasesSmoothed)
                .newDeaths(this.newDeaths)
                .newDeathsSmoothed(this.newDeathsSmoothed)
                .newTests(this.newTests)
                .totalCases(this.totalCases)
                .totalDeaths(this.totalDeaths)
                .totalTests(this.totalTests)
                .build();

            getAppController().updateCovidRecord(cd);
        listCovidDataBean.fillRecords();

        return "covid-data-list";
        }

    public void setListCovidDataBean(ListCovidDataBean listCovidDataBean) {
        this.listCovidDataBean = listCovidDataBean;
    }

    public String renderDate (){
        return StringFromDate(date);
    }
}
