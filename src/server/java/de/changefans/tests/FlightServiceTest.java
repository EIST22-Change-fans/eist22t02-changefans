package de.changefans.tests;
import de.changefans.model.Flight;
import de.changefans.service.FlightService;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlightServiceTest {

    @Test
    public void testGetFlights(){
        FlightService flightService = new FlightService();
        List<Flight> flights = new ArrayList<>();
        try {
            flights = flightService.getFlights(new SimpleDateFormat("yyyy-MM-dd").parse("2022-06-4"), "EDDF", "LFPG");
        } catch (ParseException e) {
            System.err.println("Could not parse the date into the given format!");
        }
        assertFalse(flights.isEmpty());

    }

}
