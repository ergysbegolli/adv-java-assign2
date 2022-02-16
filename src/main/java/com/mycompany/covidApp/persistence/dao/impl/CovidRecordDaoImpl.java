/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.covidApp.persistence.dao.impl;

import com.mycompany.covidApp.persistence.dao.interfaces.CovidRecordDao;
import com.mycompany.covidApp.persistence.entities.CovidDataRecord;
import com.mycompany.covidApp.persistence.entities.CovidDataRecordPK;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.PersistenceException;

/**
 *
 * @author Mysafir
 */
public class CovidRecordDaoImpl implements CovidRecordDao {

    @Override
    public Collection<CovidDataRecord> getAll() throws PersistenceException {
        return entityManagerSupplier
                .get().createNamedQuery("CovidDataRecord.findAll", CovidDataRecord.class)
                .getResultList();
    }

    @Override
    public void upsert(CovidDataRecord record) {
        CovidDataRecordPK recordPK = record.getCovidDataRecordPK();
        if (getByPK(CovidDataRecord.class, recordPK) != null) {
            update(record);
        } else {
            persist(record);
        }
    }

    @Override
    public List<CovidDataRecord> getCountryRecords(String isoCode) {
        return entityManagerSupplier.get()
                .createNamedQuery("CovidDataRecord.findByIsoCode", CovidDataRecord.class)
                .setParameter("isoCode", isoCode)
                .getResultList();
    }

    @Override
    public List<CovidDataRecord> getByDate(Date date) {
        return entityManagerSupplier.get()
                .createNamedQuery("CovidDataRecord.findByDate", CovidDataRecord.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<CovidDataRecord> getLatestCovidRecords() {
        return entityManagerSupplier.get()
                .createNamedQuery("CovidDataRecord.findByLatestDate", CovidDataRecord.class)
                .getResultList();
    }

    @Override
    public List<CovidDataRecord> sortCovidRecords(Date date, List<String> sorts) {
        String ql = "SELECT c FROM CovidDataRecord c WHERE c.covidDataRecordPK.date = :date ORDER BY ";

        ql += sorts.stream().map(c -> "c." + c).collect(Collectors.joining(", "));

       return entityManagerSupplier.get().createQuery(ql)
               .setParameter("date", date)
               .getResultList();
    }

    @Override
    public Collection<CovidDataRecord> getByColumnName(String columnName, String value) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
