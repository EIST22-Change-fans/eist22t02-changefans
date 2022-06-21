package de.changefans.rest;

import de.changefans.model.Passenger;
import de.changefans.service.PassengerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class PassengerResource {
    private final PassengerService passengerService;


    public PassengerResource(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping("passengers")
    public ResponseEntity<List<Passenger>> getAllFeedbacks() {
        return ResponseEntity.ok(passengerService.getAllPassengers());
    }

    @PostMapping("passengers")
    public ResponseEntity<Passenger> createFeedback(@RequestBody Passenger passenger) {
        Optional<Passenger> passenger1 = passengerService.checkExistingPassenger(passenger);
        if (passenger1.isEmpty()) {
            return ResponseEntity.ok(passengerService.savePassenger(passenger));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("passengers/{passengerId}")
    public ResponseEntity<Passenger> updatePassenger(@RequestBody Passenger updatedPassenger, @PathVariable("passengerId") UUID passengerId) {
        if (!updatedPassenger.getId().equals(passengerId)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(passengerService.savePassenger(updatedPassenger));
    }

    @DeleteMapping("passengers/{passengerId}")
    public ResponseEntity<Void> deletePerson(@PathVariable("passengerId") UUID passengerId) {
        passengerService.deletePassenger(passengerId);
        return ResponseEntity.noContent().build();
    }
}
