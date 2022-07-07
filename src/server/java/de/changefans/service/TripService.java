package de.changefans.service;
import de.changefans.model.Flight;
import de.changefans.model.Trip;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class TripService {

    private final HashMap<UUID, List<Trip>> tripData= new HashMap<>() ;

    public TripService() {
    }
    public Trip getTrip (UUID passengerId, UUID tripId) {
        for (Trip t : tripData.get(passengerId)) {
            if (t.getTripID().equals(tripId))
                return t;
        }
        return null;
    }

    public List<Trip> getTrips (UUID passengerId) {
        return tripData.get(passengerId);
    }

    public Optional<Trip> checkExistingTrip(UUID passengerId, UUID tripId) {
        Trip trip;
       if ((trip=getTrip(passengerId, tripId))!=null) {
           return Optional.of(trip);
        }
       return Optional.empty();
    }


    public Trip saveNewTrip(UUID passengerId, Trip trip) {
       List<Trip> passengerTrips= tripData.get(passengerId);
       passengerTrips.add(trip);
       return trip;
    }





    public List<Flight> deleteFlightFromTrip(UUID passengerId, UUID tripId ,UUID flightId){
        Trip trip = getTrip(passengerId, tripId);
        List<Flight> tripFlights= trip.getFlights();
        for (Flight f : tripFlights) {
            if (f.getFlightId().equals(flightId)) {
                tripFlights.remove(f);
                break;
            }

        }
        //update trip attributes
        if (tripFlights.size()==0) {
            trip.setStartDate(null);
            trip.setEndDate(null);
        }
        else {
            trip.setStartDate(tripFlights.get(0).getStartDate());
            trip.setEndDate(tripFlights.get(tripFlights.size()-1).getEndDate());
        }
        return tripFlights;
    }

   public List<Trip> deleteTrip(UUID passengerId, Trip trip){
       List<Trip> passengerTrips= tripData.get(passengerId);
       passengerTrips.remove(trip);
       return passengerTrips;
   }
     // Potentially transform flight list to tree set and implement comparator in flight
    public List<Flight> addFlightToTrip(UUID passengerId, UUID tripId , Flight flight) throws IllegalArgumentException{
        Trip trip = getTrip(passengerId, tripId);
        List<Flight> tripFlights= getTrip(passengerId,tripId).getFlights();
        Flight last= tripFlights.get(tripFlights.size()-1);
        if (last.getEndDate().before(flight.getStartDate())) {
            tripFlights.add(flight);
            trip.setStartDate(tripFlights.get(0).getStartDate());
            trip.setEndDate(tripFlights.get(tripFlights.size()-1).getEndDate());
        }
        else {
            throw new IllegalArgumentException();
        }

        return tripFlights;
    }

}
