package de.changefans.service;

import de.changefans.model.Flight;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FlightService {
    // TODO @Rayen Mhadhbi
    // TODO delegate calls to an external API, parse data and return a corresponding flight object.
    public List<Flight> getFlights(Date date, String departureICAO, String arrivalICAO) {
        return null;
    }
}
