package de.changefans.model;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Flight {

    private UUID id;
    private int flightNumber;
    private Date startDate;
    private Date endDate;
    private Place departurePlace;
    private Place arrivalPlace;
    private String departureAirport; //ICAO
    private String arrivalAirport; //ICAO
    private String gate;
    private int terminal;
    private String airline;
    private String status; //TODO status as ENUM
    private List<Passenger> passengerList;

    public Flight(int flightNumber, Date startDate, Date endDate, Place departurePlace, Place arrivalPlace) {
        this.flightNumber = flightNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.departurePlace = departurePlace;
        this.arrivalPlace = arrivalPlace;
    }

    public String showPassengerInformation(UUID passengerID) {
        Optional<Passenger> passenger = passengerList.stream().filter(p -> p.getId().equals(passengerID)).findAny();
        if (passenger.isPresent()) {
            return passenger.get().toString();
        } else return "Invalid id! Passenger didn't book this flight.";
    }

    public void requestService() {

    }

    public UUID getId() {
        return id;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Place getDeparturePlace() {
        return departurePlace;
    }

    public void setDeparturePlace(Place departurePlace) {
        this.departurePlace = departurePlace;
    }

    public Place getArrivalPlace() {
        return arrivalPlace;
    }

    public void setArrivalPlace(Place arrivalPlace) {
        this.arrivalPlace = arrivalPlace;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public int getTerminal() {
        return terminal;
    }

    public void setTerminal(int terminal) {
        this.terminal = terminal;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }
}
