package com.mycompany.covidApp.faces.sessionmanager;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


public final class SessionManager implements SessionManagerInterface {
    private static final SessionManager INSTANCE;
    private static final String USER_KEY = "EMAIL";

    static {
        INSTANCE = new SessionManager();
    }

    private SessionManager() { }

    public static SessionManager getInstance() {
        return INSTANCE;
    }


    public <T> void addObjectToSession(String key, T t) {
        getSession(false).setAttribute(key, t);
    }

    public void removeObjectFromSession(String key) {
        getSession(false).removeAttribute(key);
    }

    public <T> T getObjectFromSession(Class<T> oClass, String key) {
        return (T) getSession(false).getAttribute(key);
    }

    public void createSession(String email) {

        final HttpSession session = getSession(true);
        session.setAttribute(USER_KEY, email);
    }

    private HttpSession getSession(boolean create) {
        return (HttpSession) getExternalContext()
                .getSession(create);
    }

    public ExternalContext getExternalContext() {
        return FacesContext
                .getCurrentInstance()
                .getExternalContext();
    }

    public void invalidateSession() {
        getExternalContext()
                .invalidateSession();
    }


    public boolean hasLoggedUser() {
        try {
            return !getLoggedInUserEmail().isEmpty();
        }
        catch (NullPointerException e) {
            System.err.println("No active session!");
            return false;
        }
    }

    public String getLoggedInUserEmail() {
        return (String) getSession(false)
                .getAttribute(USER_KEY);
    }

}
