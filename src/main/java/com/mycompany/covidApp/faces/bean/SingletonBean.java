package com.mycompany.covidApp.faces.bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class SingletonBean {

    private List<String> continents;

    public SingletonBean() {
        continents = new ArrayList<>();
        continents.add("Africa");
        continents.add("Antarctica");
        continents.add("Asia");
        continents.add("Europe");
        continents.add("North America");
        continents.add("Oceania");
        continents.add("South America");
    }

    public List<String> getContinents() {
        return continents;
    }



}