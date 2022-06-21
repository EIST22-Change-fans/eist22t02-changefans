package de.changefans.model;

import java.util.List;
import java.util.UUID;

public class Passenger {

    private UUID id;
    private String name;
    private String surname;
    private List<String> couponCodes;
    private List<Flight> flightList;
    private List<POI> favoritePOIs;
    private List<Trip> trips;

    public Passenger(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void notifyPassenger() {

    }

    public void savePOI() {

    }

    public void saveFlight() {

    }

    public void addCouponCode(String coupon) {
        couponCodes.add(coupon);
    }

    @Override
    public String toString() {
        return "Passenger: " + name + " " + surname + ". ID:" + id;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<String> getCouponCodes() {
        return couponCodes;
    }

    public void setCouponCodes(List<String> couponCodes) {
        this.couponCodes = couponCodes;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public List<POI> getFavoritePOIs() {
        return favoritePOIs;
    }

    public void setFavoritePOIs(List<POI> favoritePOIs) {
        this.favoritePOIs = favoritePOIs;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
