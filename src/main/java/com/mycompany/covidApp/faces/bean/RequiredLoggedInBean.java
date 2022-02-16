package com.mycompany.covidApp.faces.bean;

import com.mycompany.covidApp.faces.FacesUtil;
import com.mycompany.covidApp.faces.sessionmanager.SessionManager;

import javax.annotation.PostConstruct;

public class RequiredLoggedInBean {
    @PostConstruct
    public void init() throws IllegalStateException {

        if (!SessionManager.getInstance().hasLoggedUser()) {
            FacesUtil.redirect(FacesUtil.LOGIN_URI);
            throw new IllegalStateException("Redirected!");
        }

    }

}
