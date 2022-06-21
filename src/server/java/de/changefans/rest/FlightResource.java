package de.changefans.rest;

import de.changefans.model.Flight;
import de.changefans.service.FlightService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class FlightResource {

    private final FlightService flightService;

    public FlightResource(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("flights")
    public ResponseEntity<List<Flight>> getFlights(@RequestBody Date date,
                                                   @RequestBody String departureICAO,
                                                   @RequestBody String arrivalICAO) {
        return ResponseEntity.ok(flightService.getFlights(date, departureICAO, arrivalICAO));
    }
}
