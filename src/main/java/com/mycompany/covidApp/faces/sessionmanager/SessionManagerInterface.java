package com.mycompany.covidApp.faces.sessionmanager;

import com.mycompany.covidApp.persistence.entities.User;

public interface SessionManagerInterface {

    void createSession(String email);

    void invalidateSession();

    boolean hasLoggedUser();

    String getLoggedInUserEmail();
}
