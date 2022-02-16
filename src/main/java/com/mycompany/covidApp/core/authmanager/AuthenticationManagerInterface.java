/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.covidApp.core.authmanager;

import com.mycompany.covidApp.persistence.entities.User;

/**
 *
 * @author Mysafir
 */
public interface AuthenticationManagerInterface {
    User tryAuth(String email, String password) throws SecurityException;
}
