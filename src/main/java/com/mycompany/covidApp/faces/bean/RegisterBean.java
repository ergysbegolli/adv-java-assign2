package com.mycompany.covidApp.faces.bean;

import com.mycompany.covidApp.core.controller.AppController;
import com.mycompany.covidApp.faces.FacesUtil;
import com.mycompany.covidApp.faces.sessionmanager.SessionManager;
import com.mycompany.covidApp.persistence.entities.User;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class RegisterBean {

    private String name;
    private String surname;
    private String email;
    private String role;
    private String password;
    private boolean duplicateEmailProvided;

    @PostConstruct
    public void init() {
        if (SessionManager.getInstance().hasLoggedUser()) {
            FacesUtil.redirect(FacesUtil.INDEX_URI);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void register() {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        try {
            setDuplicateEmailProvided(false);
            AppController.getInstance().registerUser(user);
            FacesUtil.redirect(FacesUtil.LOGIN_URI);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            setDuplicateEmailProvided(true);
        }
    }

    public boolean isDuplicateEmailProvided() {
        return duplicateEmailProvided;
    }

    public void setDuplicateEmailProvided(boolean duplicateEmailProvided) {
        this.duplicateEmailProvided = duplicateEmailProvided;
    }
}
