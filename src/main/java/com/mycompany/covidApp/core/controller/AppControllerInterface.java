/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.covidApp.core.controller;

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
public interface AppControllerInterface {

   User authenticate(String email, String password) throws SecurityException;

    void registerUser(User user) throws PersistenceException, DatabaseException;

    User getUserByPK(String email);
    
    void editUser(User user);

    Collection<Country> getAllCountries();

    List<Country> filterCountries(String name, String continent);

    Collection<CovidDataRecord> getAllCovidDataRecords();

    List<CovidDataRecord> getLatestCovidDataRecords();

    List<CovidDataRecord> filterCovidData(Date date);
    
    boolean checkIfCountryExist(String iso_code);

    void addNewCountry(Country country);

    Country getCountryByPK(String iso_code);

    void updateCountry(Country country);

    void deleteCoutnry(Country country);

    void addCovidDataRecord(CovidDataRecord record);

    CovidDataRecord getCovidDataByPK (CovidDataRecordPK recordPK);
    
    void updateCovidRecord(CovidDataRecord record);
    
    void deleteCovidRecord(CovidDataRecord record);
 List<CovidDataRecord> getCountryRecords (String isoCode);

 List<CovidDataRecord> sortCovidRecords (Date date, List<String> sorts);
 List<Country> sortCountries (String continent, String name, List<String> sorts);
}
