/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.covidApp.persistence.dao.impl;

import com.mycompany.covidApp.persistence.dao.interfaces.UserDao;
import com.mycompany.covidApp.persistence.entities.User;
import java.util.Collection;
import javax.persistence.PersistenceException;

/**
 *
 * @author Mysafir
 */
public class UserDaoImpl implements UserDao {

    @Override
    public Collection<User> getAll() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(User entity) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<User> getByColumnName(String columnName, String value) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
