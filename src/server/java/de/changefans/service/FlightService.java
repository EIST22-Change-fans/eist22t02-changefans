package de.changefans.service;


import de.changefans.model.Flight;
import de.changefans.model.Place;
import org.springframework.stereotype.Service;

import org.json.*;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;


@Service
public class FlightService {

    private final String FlIGHT_API_KEY = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI0IiwianRpIjoiMjAwNmY3YWM2ODkyMGY5NmNmOGIxMzI0NmM5Mjg1ZTg1MDNhOGNkYzAzZmI1NzA4M2I5YWMxODYzYWMyNjE3MWY1NGE2MTNlNDI5YjhjODEiLCJpYXQiOjE2NTU5MzU1MTcsIm5iZiI6MTY1NTkzNTUxNywiZXhwIjoxNjg3NDcxNTE3LCJzdWIiOiI2ODc0Iiwic2NvcGVzIjpbXX0.KSE_CrVi-H5dT8uoRs85L8Iyt2BcPXtQPBIX7yWe829fQipEwk81PFED_Wa651P5vlKrOqCfYJsE4RhizDympA";

    public List<Flight> getFlights(Date date, String departureICAO, String arrivalICAO) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);

        //TODO filter?
        final String uri = "https://app.goflightlabs.com/flights?access_key=" + FlIGHT_API_KEY;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        result = "{\n" +
                "                    \"data\":" + result + "}";
        JSONObject obj = new JSONObject(result);
        JSONArray arr = obj.getJSONArray("data");
        List<Flight> flights = new ArrayList<>();
        //TODO extract more data from the flight json
        for (int i = 0; i < arr.length(); i++) {
            JSONObject fl = arr.getJSONObject(i).getJSONObject("flight");
            int flightNumber = Integer.parseInt(fl.getString("number"));
            JSONObject dep = arr.getJSONObject(i).getJSONObject("departure");
            Instant instant1 = Instant.parse(dep.getString("scheduled"));
            Date startDate = Date.from(instant1);
            JSONObject arrv = arr.getJSONObject(i).getJSONObject("arrival");
            Instant instant2 = Instant.parse(arrv.getString("scheduled"));
            Date endDate = Date.from(instant2);

            String depName = dep.getString("iata");
            String arrName = arrv.getString("iata");
            Place departurePlace = new Place(depName);
            Place arrivalPlace = new Place(arrName);

            Flight flight = new Flight(flightNumber, startDate, endDate, departurePlace, arrivalPlace);
            flights.add(flight);

        }

        return flights;
    }

    //TODO use for testing
    public static void main(String[] args) throws ParseException {
        FlightService flightService = new FlightService();
        List<Flight> flights = flightService.getFlights(new SimpleDateFormat("yyyy-MM-dd").parse("2022-06-25"), "EDDF", "LFPG");
        System.out.println("");
    }


}





