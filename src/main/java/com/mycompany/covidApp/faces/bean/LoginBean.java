package com.mycompany.covidApp.faces.bean;

import com.mycompany.covidApp.core.controller.AppController;
import com.mycompany.covidApp.faces.FacesUtil;
import com.mycompany.covidApp.faces.sessionmanager.SessionManager;
import com.mycompany.covidApp.persistence.entities.User;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class LoginBean {
    private final SessionManager sessionManager = SessionManager.getInstance();
    private String email;
    private String password;
    private boolean redirectedForValidation;
    private String failed = "";

    @PostConstruct
    public void init() {
        if (sessionManager.hasLoggedUser())
            FacesUtil.redirect(FacesUtil.INDEX_URI);
        else {
            checkRequestParameterValueForErrorMessage();
        }
    }

    private void checkRequestParameterValueForErrorMessage() {
        try {
            String errorParam = FacesUtil.getRequestParameterValue(FacesUtil.LOGIN_ERROR_PARAM);
            setRedirectedForValidation(errorParam !=null && errorParam.equals(FacesUtil.LOGIN_ERROR_ARG));
        } catch (NullPointerException e) {
            setRedirectedForValidation(false);
        }
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

    public void authenticate() {
        try {
            User user = AppController
                    .getInstance()
                    .authenticate(email, password);

            sessionManager.createSession(user.getEmail());
            setFailed("");
            FacesUtil.redirect(FacesUtil.INDEX_URI);

        }
        catch (SecurityException e) {
            System.out.println(e.getMessage());
            setFailed(e.getMessage());
        }
    }

    public boolean isRedirectedForValidation() {
        return redirectedForValidation;
    }

    public void setRedirectedForValidation(boolean redirectedForValidation) {
        this.redirectedForValidation = redirectedForValidation;
    }

    public boolean isFailed(){
        return !this.failed.isEmpty();
    }

    public String getFailed(){
        return failed;
    }

    public void setFailed(String failed){
        this.failed = failed;
    }
}
