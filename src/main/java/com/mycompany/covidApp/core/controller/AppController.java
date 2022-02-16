/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.covidApp.core.controller;

import com.mycompany.covidApp.core.authmanager.AuthenticationManager;
import com.mycompany.covidApp.faces.FacesUtil;
import com.mycompany.covidApp.persistence.dao.factory.DaoFactory;
import com.mycompany.covidApp.persistence.entities.Country;
import com.mycompany.covidApp.persistence.entities.CovidDataRecord;
import com.mycompany.covidApp.persistence.entities.CovidDataRecordPK;
import com.mycompany.covidApp.persistence.entities.User;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.PersistenceException;
import org.eclipse.persistence.exceptions.DatabaseException;

/**
 *
 * @author Mysafir
 */
public class AppController implements AppControllerInterface {
    
    private static final AppController INSTANCE;
    
    static {
        INSTANCE = new AppController();
    }
    
    private AppController() {
    }
    
    public static AppController getInstance() {
        return INSTANCE;
    }
    
    @Override
    public User authenticate(String email, String password) throws SecurityException {
        return AuthenticationManager.getInstance()
                .tryAuth(email, password);
    }
    
    @Override
    public void registerUser(User user) throws PersistenceException, DatabaseException {
        DaoFactory.getUserDao().persist(user);
    }

    @Override
    public User getUserByPK(String email) {
        return  DaoFactory.getUserDao().getByPK(User.class, email);
    }

    @Override
    public Collection<Country> getAllCountries() {
        return DaoFactory.getCountryDao().getAll();
    }

    @Override
    public List<Country> filterCountries(String name, String continent) {
        return DaoFactory.getCountryDao().applyFilters(name, continent);
    }

    @Override
    public Collection<CovidDataRecord> getAllCovidDataRecords() {
        return DaoFactory.getCovidRecordDao().getAll();
    }

    @Override
    public List<CovidDataRecord> getLatestCovidDataRecords() {
        return DaoFactory.getCovidRecordDao().getLatestCovidRecords();
    }

    @Override
    public List<CovidDataRecord> filterCovidData(Date date) {
        return DaoFactory.getCovidRecordDao().getByDate(date);
    }

    @Override
    public void addNewCountry(Country country) {
        DaoFactory.getCountryDao().persist(country);
    }
    
    @Override
    public Country getCountryByPK(String iso_code) {
        return DaoFactory.getCountryDao().getByPK(Country.class, iso_code);
    }
    
    @Override
    public void updateCountry(Country country) {
        DaoFactory.getCountryDao().update(country);
    }
    
    @Override
    public void deleteCoutnry(Country country) {
        DaoFactory.getCountryDao().delete(country);
    }
    
    @Override
    public void addCovidDataRecord(CovidDataRecord record) {
        DaoFactory.getCovidRecordDao().upsert(record);
    }
    
    @Override
    public CovidDataRecord getCovidDataByPK(CovidDataRecordPK recordPK) {
        return DaoFactory.getCovidRecordDao().getByPK(CovidDataRecord.class, recordPK);
    }
    
    @Override
    public void updateCovidRecord(CovidDataRecord record) {
        DaoFactory.getCovidRecordDao().update(record);
    }
    
    @Override
    public void deleteCovidRecord(CovidDataRecord record) {
        DaoFactory.getCovidRecordDao().delete(record);
    }

    @Override
    public List<CovidDataRecord> getCountryRecords(String isoCode) {
        return DaoFactory.getCovidRecordDao().getCountryRecords(isoCode);
    }

    @Override
    public List<CovidDataRecord> sortCovidRecords(Date date, List<String> sorts) {
        return DaoFactory.getCovidRecordDao().sortCovidRecords(date, sorts);
    }

    @Override
    public List<Country> sortCountries(String continent, String name, List<String> sorts) {
        return DaoFactory.getCountryDao().sortCountries(continent, name, sorts);
    }

    @Override
    public void editUser(User user) {
        DaoFactory.getUserDao().update(user);
    }

    @Override
    public boolean checkIfCountryExist(String iso_code) {
        return DaoFactory.getCountryDao().exist(iso_code);
    }
}
