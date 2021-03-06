package com.mycompany.covidApp.faces.method;

import com.mycompany.covidApp.faces.FacesUtil;
import com.mycompany.covidApp.faces.sessionmanager.SessionManager;


/**
 * Represents a method which can be called without checking for permission explicitly
 * Use the {@link #call()} to call the method with permission check
 * @param <R> return type of the method
 */

@FunctionalInterface
public interface RequiresLoginMethodNoParam<R> {


    /**
     * @return the result of the method
     * called by {@link #call()}
     */
    R function();

    /**
     * @return the result of the method
     * @throws SecurityException if there is no logged user
     */
    default R call() throws SecurityException {
        if (SessionManager.getInstance().hasLoggedUser())
            return function();
        else
            FacesUtil.redirect(FacesUtil.LOGIN_URI, FacesUtil.LOGIN_ERROR_PARAM, FacesUtil.LOGIN_ERROR_ARG);
        return null;
    }
}
