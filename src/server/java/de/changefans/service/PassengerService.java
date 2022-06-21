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

    public Passenger savePassenger(Passenger passenger) {
        passengers.add(passenger);
        return passenger;
    }

    public Optional<Passenger> getPassenger(UUID passengerId) {
        return passengers.stream().filter(p -> p.getId().equals(passengerId)).findAny();
    }

    public void deletePassenger(UUID passengerId) {
        this.passengers.removeIf(passenger -> passenger.getId().equals(passengerId));
    }

    public Optional<Passenger> checkExistingPassenger(Passenger passenger) {
        for (Passenger p : passengers) {
            if (p.getId() == passenger.getId()) {
                return Optional.of(passenger);
            }
        }
        return Optional.empty();
    }

    public List<Passenger> getAllPassengers() {
        return Collections.unmodifiableList(this.passengers);
    }

}
