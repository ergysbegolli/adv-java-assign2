package com.mycompany.covidApp.ws;

import com.mycompany.covidApp.core.controller.AppController;
import com.mycompany.covidApp.models.CountryServiceRequest;
import com.mycompany.covidApp.persistence.entities.Country;
import com.mycompany.covidApp.persistence.entities.CovidDataRecord;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("records")
public class CountryService {

    private AppController getAppController() {
        return AppController.getInstance();
    }

    @GET
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<Country, List<CovidDataRecord>> getCourses(CountryServiceRequest request) {
        Map<Country, List<CovidDataRecord>> response = new HashMap<>();
        List<Country> countries;

        if (request.getCountryCodes().isEmpty())
            countries = (List) getAppController().getAllCountries();
        else countries = request
                .getCountryCodes()
                .stream()
                .map(code -> getAppController().getCountryByPK(code))
                .collect(Collectors.toList());

        if (request.getContinent() != null)
            countries = countries
                    .stream()
                    .filter(country -> country.getContinent() == request.getContinent())
                    .collect(Collectors.toList());

        countries
                .stream()
                .forEach(x -> response.put(x,  getAppController().getCountryRecords(x.getIsoCode())));

        return response;
    }
}
