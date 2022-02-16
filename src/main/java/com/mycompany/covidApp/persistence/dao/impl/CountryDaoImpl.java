/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.covidApp.persistence.dao.impl;

import com.mycompany.covidApp.persistence.dao.interfaces.CountryDao;
import com.mycompany.covidApp.persistence.entities.Country;
import com.mycompany.covidApp.persistence.entities.CovidDataRecord;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mysafir
 */
public class CountryDaoImpl implements CountryDao {

    @Override
    public Collection<Country> getAll() throws PersistenceException {
        return entityManagerSupplier
                .get()
                .createNamedQuery("Country.findAll", Country.class)
                .getResultList();
    }

    @Override
    public void delete(Country country) throws PersistenceException {
        EntityManager entityManager = entityManagerSupplier.get();
        try {
            entityManager.getTransaction().begin();
            if (!entityManager.contains(country)) {
                country = entityManager.merge(country);
            }

            TypedQuery<CovidDataRecord> deleteCovidDataByIsoCode = entityManager.createNamedQuery("CovidDataRecord.deleteCovidDataByIsoCode", CovidDataRecord.class);
            deleteCovidDataByIsoCode.setParameter("isoCode", country.getIsoCode());
            deleteCovidDataByIsoCode.executeUpdate();

            entityManager.remove(country);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public boolean exist(String isoCode) {
        return getByPK(Country.class, isoCode) != null;
    }

    @Override
    public List<Country> getByContinent(String continent) {
        return entityManagerSupplier.get()
                .createNamedQuery("Country.findByContinent", Country.class)
                .setParameter("continent", continent)
                .getResultList();
    }

    @Override
    public List<Country> getByName(String name) {
        return entityManagerSupplier.get()
                .createNamedQuery("Country.findByNamePattern", Country.class)
                .setParameter("name", name + "%")
                .getResultList();
    }

    @Override
    public List<Country> getByNameAndContinent(String name, String continent) {
        return entityManagerSupplier.get()
                .createNamedQuery("Country.findByContinentAndNamePattern", Country.class)
                .setParameter("name", name + "%")
                .setParameter("continent", continent)
                .getResultList();
    }

    @Override
    public List<Country> applyFilters(String name, String continent) {
        if(name == null && continent == null)
            return (List) getAll();
        if (name != null && continent ==null)
            return getByName(name);
        else if (name == null)
            return getByContinent(continent);
        return getByNameAndContinent(name, continent);
    }

    private boolean isParameterPresent(String paramValue) {
        return paramValue != null && !paramValue.trim().isEmpty();
    }

    @Override
    public List<Country> sortCountries(String continent, String name, List<String> sorts) {
        String ql = "SELECT c FROM Country c WHERE c.isoCode = c.isoCode ";
        if (isParameterPresent(name))
            ql += "AND LOWER(c.location) LIKE LOWER(:name) ";
        if (isParameterPresent(continent))
            ql += "AND c.continent = :continent ";

        ql += "ORDER BY " + sorts.stream().map(c -> "c." + c).collect(Collectors.joining(", "));

        Query q = entityManagerSupplier.get().createQuery(ql);

        if(isParameterPresent(name))
            q.setParameter("name", name);
        if(isParameterPresent(continent))
            q.setParameter("continent", continent);

        return q.getResultList();

    }

    @Override
    public Collection<Country> getByColumnName(String columnName, String value) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
