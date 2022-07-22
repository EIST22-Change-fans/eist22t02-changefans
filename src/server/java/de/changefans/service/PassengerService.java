package de.changefans.service;

import de.changefans.model.Passenger;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PassengerService {

    private final List<Passenger> passengers;

    public PassengerService() {
        this.passengers = new ArrayList<>();
    }

    /**
     * saves parameter passenger to a passenger list and returns passenger
     */
    public Passenger savePassenger(Passenger passenger) {
        passengers.add(passenger);
        return passenger;
    }

    /**
     * searches for a passenger in the passenger list with an id equal to the passenger id
     * given as parameter. If a passenger is found an Optional.Of(passenger) is returned
     * otherwise Optional.empty()
     */
    public Optional<Passenger> getPassenger(UUID passengerId) {
        return passengers.stream().filter(p -> p.getId().equals(passengerId)).findAny();
    }

    /**
     * deletes the passenger with the given id from the passenger list if it is an element of the list
     */
    public void deletePassenger(UUID passengerId) {
        this.passengers.removeIf(passenger -> passenger.getId().equals(passengerId));
    }

    /**
     * checks if the parameter passenger exists in the passenger list by comparing its id to the ids of all
     * passengers in the list. If it is found an Optional.of(passenger) is returned otherwise an Optional.empty()
     */
    public Optional<Passenger> checkExistingPassenger(Passenger passenger) {
        for (Passenger p : passengers) {
            if (p.getId() == passenger.getId()) {
                return Optional.of(passenger);
            }
        }
        return Optional.empty();
    }

    /**
     * returns the passenger list containing all saved passengers
     */
    public List<Passenger> getAllPassengers() {
        return Collections.unmodifiableList(this.passengers);
    }

}
