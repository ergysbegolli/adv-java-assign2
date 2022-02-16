/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.covidApp.persistence.dao.interfaces;

import com.mycompany.covidApp.persistence.entities.Country;

import java.util.List;

/**
 *
 * @author Mysafir
 */
public interface CountryDao extends ModelDao<Country, String> {
    boolean exist(String isoCode);

    List<Country> getByContinent(String continent);
    List<Country> getByName(String name);
    List<Country> getByNameAndContinent(String name, String continent);
    List<Country> applyFilters(String name, String continent);
    List<Country> sortCountries(String continent, String name, List<String> sorts);
}
