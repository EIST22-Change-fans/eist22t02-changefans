package de.changefans.service;


import de.changefans.model.Flight;
import de.changefans.model.Place;
import org.springframework.stereotype.Service;

import org.json.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;


@Service
public class FlightService {


    public List<Flight> getFlights(Date date, String departureICAO, String arrivalICAO)  {


        HttpClient httpClient = HttpClient.newHttpClient();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);

        HttpRequest getRequest;
        try {
            String FlIGHT_API_KEY = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI0IiwianRpIjoiMjAwNmY3YWM2ODkyMGY5NmNmOGIxMzI0NmM5Mjg1ZTg1MDNhOGNkYzAzZmI1NzA4M2I5YWMxODYzYWMyNjE3MWY1NGE2MTNlNDI5YjhjODEiLCJpYXQiOjE2NTU5MzU1MTcsIm5iZiI6MTY1NTkzNTUxNywiZXhwIjoxNjg3NDcxNTE3LCJzdWIiOiI2ODc0Iiwic2NvcGVzIjpbXX0.KSE_CrVi-H5dT8uoRs85L8Iyt2BcPXtQPBIX7yWe829fQipEwk81PFED_Wa651P5vlKrOqCfYJsE4RhizDympA";
            getRequest = HttpRequest.newBuilder().uri(new URI("https://app.goflightlabs.com/flights?access_key=" + FlIGHT_API_KEY +"&arr_scheduled_time_dep=" + strDate +"&dep_icao=" + departureICAO + "&arr_icao=" + arrivalICAO)).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException("URISyntax error!");
        }
        HttpResponse<String> getResponse;
        try {
            getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException("error sending/receiving request/response!");
        } catch (InterruptedException e) {
            throw new RuntimeException("sending/receiving was interruped!");
        }
        JSONObject obj = new JSONObject(getResponse.body());
        JSONArray arr = obj.getJSONArray("data");
        List<Flight> flights = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            JSONObject fl = arr.getJSONObject(i).getJSONObject("flight");
            int flightNumber = Integer.parseInt(fl.getString("flight_number"));
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

            Flight flight = new Flight(flightNumber,startDate,endDate,departurePlace,arrivalPlace);
            flights.add(flight);

        }

        return flights;


    }


    }





