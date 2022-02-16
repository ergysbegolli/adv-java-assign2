package com.mycompany.covidApp.faces.bean;

import com.mycompany.covidApp.core.controller.AppController;
import com.mycompany.covidApp.faces.sessionmanager.SessionManager;
import com.mycompany.covidApp.persistence.entities.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class EditProfileBean extends RequiredLoggedInBean {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String role;

    public EditProfileBean() {
    }

    private AppController getAppController() {
        return AppController.getInstance();
    }

    private SessionManager getSessionManager() {
        return SessionManager.getInstance();
    }

    public void init() {
        super.init();
      User user = getAppController().getUserByPK(getSessionManager().getLoggedInUserEmail());
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.name = user.getName();
    this.surname = user.getSurname();
    this.role = user.getRole();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String update() {
        User user = new User(this.email, this.password, this.role);
        user.setName(this.name);
        user.setSurname(this.surname);

            getAppController().editUser(user);

            return "view-profile";
        }
    }
