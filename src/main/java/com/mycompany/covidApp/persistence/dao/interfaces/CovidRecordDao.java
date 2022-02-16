/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.covidApp.persistence.dao.interfaces;

import com.mycompany.covidApp.persistence.entities.CovidDataRecord;
import com.mycompany.covidApp.persistence.entities.CovidDataRecordPK;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Mysafir
 */
public interface CovidRecordDao extends ModelDao<CovidDataRecord, CovidDataRecordPK> {

    void upsert(CovidDataRecord record);

    List<CovidDataRecord> getCountryRecords(String isoCode);
    List<CovidDataRecord> getByDate(Date date);
    List<CovidDataRecord> getLatestCovidRecords();
    List<CovidDataRecord> sortCovidRecords(Date date, List<String> sorts);
}
