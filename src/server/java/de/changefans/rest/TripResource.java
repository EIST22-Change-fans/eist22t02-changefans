package de.changefans.rest;

import de.changefans.model.Flight;
import de.changefans.model.Trip;
import de.changefans.service.TripService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})


public class TripResource {
    private final TripService tripService;

    public TripResource(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("passengers/{passengerId}/trips/{tripId}")
    public ResponseEntity<Trip> getTrip(@PathVariable("passengerId") UUID passengerId, @PathVariable("tripId") UUID tripId) {
        Trip trip= tripService.getTrip(passengerId,tripId);
        if (trip==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(trip);
    }

    @GetMapping("passengers/{passengerId}")
    public ResponseEntity<List<Trip>> getTrips(@PathVariable("passengerId") UUID passengerId){
       return ResponseEntity.ok(tripService.getTrips(passengerId));
    }



    @PostMapping("passengers/{passengerId}/trips")
    public ResponseEntity<Trip> createTrip(@PathVariable("passengerId") UUID passengerId, @RequestBody Trip trip) {
        Optional<Trip> t = tripService.checkExistingTrip(passengerId, trip.getTripID());
        if (t.isEmpty()) {
            return ResponseEntity.ok(tripService.saveNewTrip(passengerId ,trip));
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("passengers/{passengerId}/trips")
    public ResponseEntity<List<Trip>> deleteTrip (@PathVariable("passengerId") UUID passengerId,@RequestBody Trip trip) {
        return ResponseEntity.ok(tripService.deleteTrip(passengerId, trip));
    }

    @DeleteMapping("passengers/{passengerId}/trips/{tripId}/flights/{flightId}")
    public ResponseEntity<List<Flight>> deleteFlightFromTrip (@PathVariable("passengerId") UUID passengerId, @PathVariable("tripId") UUID tripId, @PathVariable("flightId") UUID flightId) {
        return ResponseEntity.ok(tripService.deleteFlightFromTrip(passengerId, tripId, flightId));
    }

    @PutMapping("passengers/{passengerId}/trips/{tripId}/flights")
    public ResponseEntity<List<Flight>> addFlightToTrip (@PathVariable("passengerId") UUID passengerId,@PathVariable("tripId") UUID tripId, @RequestBody Flight flight) {
        try{
            return ResponseEntity.ok(tripService.addFlightToTrip(passengerId, tripId, flight));
        } catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


}






