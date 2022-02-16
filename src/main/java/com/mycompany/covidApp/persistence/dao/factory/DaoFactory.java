/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.covidApp.persistence.dao.factory;

import com.mycompany.covidApp.persistence.dao.impl.CountryDaoImpl;
import com.mycompany.covidApp.persistence.dao.impl.CovidRecordDaoImpl;
import com.mycompany.covidApp.persistence.dao.impl.UserDaoImpl;
import com.mycompany.covidApp.persistence.dao.interfaces.CountryDao;
import com.mycompany.covidApp.persistence.dao.interfaces.CovidRecordDao;
import com.mycompany.covidApp.persistence.dao.interfaces.UserDao;

/**
 *
 * @author Mysafir 
 */
public final class DaoFactory {
    private static final UserDao USER_DAO;
    private static final CountryDao COUNTRY_DAO;
    private static final CovidRecordDao COVID_RECORD_DAO;

    static {
        USER_DAO = new UserDaoImpl();
        COUNTRY_DAO = new CountryDaoImpl();
        COVID_RECORD_DAO = new CovidRecordDaoImpl();
    }

    public static UserDao getUserDao() {
        return USER_DAO;
    }

    public static CountryDao getCountryDao() {
        return COUNTRY_DAO;
    }

    public static CovidRecordDao getCovidRecordDao() {
        return COVID_RECORD_DAO;
    }

    private DaoFactory() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    
}