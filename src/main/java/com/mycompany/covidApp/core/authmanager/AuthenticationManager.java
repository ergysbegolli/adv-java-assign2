/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.covidApp.core.authmanager;

import com.mycompany.covidApp.persistence.dao.factory.DaoFactory;
import com.mycompany.covidApp.persistence.entities.User;

/**
 *
 * @author Mysafir
 */
public final class AuthenticationManager implements AuthenticationManagerInterface {

    private static final AuthenticationManager AUTHENTICATION_MANAGER;

    static {
        AUTHENTICATION_MANAGER = new AuthenticationManager();
    }

    private AuthenticationManager() {}

    public static AuthenticationManager getInstance() {
        return AUTHENTICATION_MANAGER;
    }

    @Override
    public User tryAuth(String email, String password) throws SecurityException {
        User user = DaoFactory
                .getUserDao()
                .getByPK(User.class, email);

        if (user == null)
            throw new SecurityException("Wrong email!");

        if (!user.getPassword().equals(password))
            throw new SecurityException("Wrong password!");

        System.out.println("Login OK!");
        return user;
    }
}
