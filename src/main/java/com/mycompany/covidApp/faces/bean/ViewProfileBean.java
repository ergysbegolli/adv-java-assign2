package com.mycompany.covidApp.faces.bean;

import com.mycompany.covidApp.core.controller.AppController;
import com.mycompany.covidApp.faces.sessionmanager.SessionManager;
import com.mycompany.covidApp.persistence.entities.Country;
import com.mycompany.covidApp.persistence.entities.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class ViewProfileBean extends RequiredLoggedInBean {
    private User user;

    public ViewProfileBean() {
    }

    private AppController getAppController() {
        return AppController.getInstance();
    }

    private SessionManager getSessionManager() {
        return SessionManager.getInstance();
    }

    public void init() {
        super.init();
       user = getAppController().getUserByPK(getSessionManager().getLoggedInUserEmail());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
