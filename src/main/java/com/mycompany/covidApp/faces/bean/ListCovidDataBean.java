package com.mycompany.covidApp.faces.bean;

import com.mycompany.covidApp.core.controller.AppController;
import com.mycompany.covidApp.persistence.entities.CovidDataRecord;
import com.mycompany.covidApp.persistence.entities.CovidDataRecordPK;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mycompany.covidApp.utils.Utilities.StringFromDate;

@SessionScoped
@ManagedBean
public class ListCovidDataBean {

    private Date filterDate;

    private List<String> sorts = new ArrayList<>();
    private List<CovidDataRecord> records;

    private AppController getAppController() {
        return AppController.getInstance();
    }

    public ListCovidDataBean() {
    }

    @PostConstruct
    public void init() {
        fillRecords();
    }

    public void fillRecords() {
        this.records = getAppController().getLatestCovidDataRecords();
    }

    public List<CovidDataRecord> getRecords() {
        return records;
    }

    public void setRecords(List<CovidDataRecord> records) {
        this.records = records;
    }

    public Date getFilterDate() {
        return filterDate;
    }

    public void setFilterDate(Date filterDate) {
        this.filterDate = filterDate;
    }

    public String filter() {
        if (filterDate != null) {
            records = getAppController().filterCovidData(filterDate);
        } else {
            fillRecords();
        }
        return "";
    }

    public String clear() {
        fillRecords();
        filterDate = null;
        return "";
    }

    public boolean renderClear() {
        return filterDate != null;
    }

    public String formatDateParam(Date date) {
        return StringFromDate(date);
    }

    public String deleteRecord(CovidDataRecordPK cdpk) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("covidPU");
        EntityManager em = emf.createEntityManager();
        CovidDataRecord cd = em.find(CovidDataRecord.class, cdpk);
        em.getTransaction().begin();
        em.remove(cd);
        em.getTransaction().commit();
//      idk why this was not working
//      getAppController().deleteCovidRecord(getAppController().getCovidDataByPK(cdpk));
        return clear();
    }

    public String sortIsoCode() {
        return sort("covidDataRecordPK.isoCode");
    }

    public String sortTotalCases() {
        return sort("totalCases");
    }

    public String sortNewCases() {
        return sort("newCases");
    }

    public String sortNewCasesSmoothed() {
        return sort("newCasesSmoothed");
    }

    public String sortTotalDeaths() {
        return sort("totalDeaths");
    }

    public String sortNewDeaths() {
        return sort("newDeaths");
    }

    public String sortNewDeathsSmoothed() {
        return sort("newDeathsSmoothed");
    }

    public String sortTotalTests() {
        return sort("totalTests");
    }

    public String sortNewTests() {
        return sort("newTests");
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
            records = getAppController().sortCovidRecords(records.get(0).getCovidDataRecordPK().getDate(), sorts);
        }

        return "";
    }
}
