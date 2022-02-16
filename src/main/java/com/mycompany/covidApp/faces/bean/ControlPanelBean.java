package com.mycompany.covidApp.faces.bean;

import com.mycompany.covidApp.faces.FacesUtil;
import com.mycompany.covidApp.faces.sessionmanager.SessionManager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ControlPanelBean {

    private final SessionManager sessionManager = SessionManager.getInstance();

    public void logOutAction() {
        sessionManager.invalidateSession();
        FacesUtil.redirect(FacesUtil.INDEX_URI);
    }

    private boolean isUserLoggedIn() {
        return sessionManager.hasLoggedUser();
    }

    public boolean isUserPanelRendered() {
        return isUserLoggedIn();
    }

    public boolean isLogInPanelRendered() {
        return !isUserLoggedIn();
    }
}
