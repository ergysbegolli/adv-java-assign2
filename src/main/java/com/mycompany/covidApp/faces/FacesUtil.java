package com.mycompany.covidApp.faces;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class FacesUtil {

    public static final String INDEX_URI = "index.xhtml";
    public static final String COUNTRY_LIST = "country-list.xhtml";
    public static final String LOGIN_URI = "login.xhtml";
    public static final String LOGIN_ERROR_PARAM = "loginerr";
    public static final String LOGIN_ERROR_ARG = "loginerr";

    private FacesUtil() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static void redirect(String url, String argumentName, String argumentValue) {
        String urlArguments = "?faces-redirect=true";
        if(argumentName != null && argumentValue != null) {
            urlArguments += "&" + argumentName + "=" + argumentValue;
        }
        try {
            getFacesExternalContext()
                    .redirect(url + urlArguments);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void redirect(String url) {
        System.out.println("URL" + url);
        redirect(url, null, null);
    }

    private static FacesContext getFacesContextInstance() {
        return FacesContext.getCurrentInstance();
    }

    private static ExternalContext getFacesExternalContext() {
        return getFacesContextInstance().getExternalContext();
    }

    public static HttpServletRequest getCurrentRequest() {
        return (HttpServletRequest) getFacesExternalContext().getRequest();
    }

    public static String getRequestParameterValue(String param) {
        return getCurrentRequest().getParameter(param);
    }
}
