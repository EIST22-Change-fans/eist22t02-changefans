package de.changefans.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

public class Trip {
    private LinkedList<Flight> flights;
    private Date startDate;
    private Date endDate;

    private UUID tripID;

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

    public UUID getTripID() {
        return tripID;
    }

    public void setTripID(UUID tripID) {
        this.tripID = tripID;
    }

    public LinkedList<Flight> getFlights() {
        return flights;
    }

    public void setFlights(LinkedList<Flight> flights) {
        this.flights = flights;
    }
}
