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
import java.util.*;


@Service

public class FlightService {

    private final String FlIGHT_API_KEY = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI0IiwianRpIjoiMjAwNmY3YWM2ODkyMGY5NmNmOGIxMzI0NmM5Mjg1ZTg1MDNhOGNkYzAzZmI1NzA4M2I5YWMxODYzYWMyNjE3MWY1NGE2MTNlNDI5YjhjODEiLCJpYXQiOjE2NTU5MzU1MTcsIm5iZiI6MTY1NTkzNTUxNywiZXhwIjoxNjg3NDcxNTE3LCJzdWIiOiI2ODc0Iiwic2NvcGVzIjpbXX0.KSE_CrVi-H5dT8uoRs85L8Iyt2BcPXtQPBIX7yWe829fQipEwk81PFED_Wa651P5vlKrOqCfYJsE4RhizDympA";

    /**
     * This method takes in a date, a departure place and arrival place coded as icao
     * It sends HTTP Get request based on these parameters to an external API and parses
     * the json response into the needed attributes to create a Flight Class. It then adds each
     * created flight to a flight list until there are no more flights to add and returns the
     * flight list.
     */
    public List<Flight> getFlights(Date date, String departureICAO, String arrivalICAO) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);

        final String uri = "https://app.goflightlabs.com/flights?access_key=" + FlIGHT_API_KEY + "&dep_icao=" + departureICAO + "&arr_icao=" + arrivalICAO + "&arr_scheduled_time_dep=" + strDate;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        result = "{\n" +
                "                    \"data\":" + result + "}";
        JSONObject obj = new JSONObject(result);
        if (obj.toString().contains("no flights found")) {
            return null;
        }

        JSONArray arr = obj.getJSONArray("data");
        List<Flight> flights = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++) {
            JSONObject fl = arr.getJSONObject(i).getJSONObject("flight");
            int flightNumber = Integer.parseInt(fl.getString("number"));
            JSONObject dep = arr.getJSONObject(i).getJSONObject("departure");
            Instant instant1 = Instant.parse(dep.getString("scheduled"));
            Date startDate = Date.from(instant1);
            JSONObject arrv = arr.getJSONObject(i).getJSONObject("arrival");
            Instant instant2 = Instant.parse(arrv.getString("scheduled"));
            Date endDate = Date.from(instant2);

            String depName = dep.getString("icao");
            String arrName = arrv.getString("icao");
            Place departurePlace = new Place(depName);
            Place arrivalPlace = new Place(arrName);

            String departureResult = restTemplate.getForObject("http://iatageo.com/getICAOLatLng/" + depName, String.class);
            String arrivalResult = restTemplate.getForObject("http://iatageo.com/getICAOLatLng/" + arrName, String.class);

            JSONObject departureJSON = new JSONObject(departureResult);
            departurePlace.setLatitude(departureJSON.getDouble("latitude"));
            departurePlace.setLongitude(departureJSON.getDouble("longitude"));

            JSONObject arrivalJSON = new JSONObject(arrivalResult);
            arrivalPlace.setLatitude(arrivalJSON.getDouble("latitude"));
            arrivalPlace.setLongitude(arrivalJSON.getDouble("longitude"));

            String gate = dep.getString("gate");
            int terminal = dep.getInt("terminal");

            JSONObject al = arr.getJSONObject(i).getJSONObject("airline");
            String airline = al.getString("name");


            Flight flight = new Flight(flightNumber, startDate, endDate, departurePlace, arrivalPlace);
            flight.setDepartureAirport(departureICAO);
            flight.setArrivalAirport(arrivalICAO);
            flight.setGate(gate);
            flight.setTerminal(terminal);
            flight.setAirline(airline);

            flights.add(flight);

        }

        return flights;
    }


}








