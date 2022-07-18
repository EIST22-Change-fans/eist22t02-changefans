package de.changefans.rest;

import de.changefans.model.Passenger;
import de.changefans.service.TripService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

//TODO Yosr

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class TripResource {

    private final TripService tripService;

    public TripResource(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("passengers/{passengerId}/trips/{tripId}")
    public ResponseEntity<Passenger> getTrip(@PathVariable("passengerId") UUID passengerId,
                                             @PathVariable("tripId") UUID tripId) {
        return null; //TODO
    }
}
