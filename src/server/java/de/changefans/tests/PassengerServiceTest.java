package de.changefans.tests;
import de.changefans.model.Passenger;
import de.changefans.service.PassengerService;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PassengerServiceTest {
    @Test public void testSavePassenger() {
        PassengerService passengerService = new PassengerService();
        Passenger rayen = new Passenger("Rayen", "Mhadhbi", new UUID(11,111));
        assertEquals(rayen, passengerService.savePassenger(rayen));

    }

    @Test
    public void testGetPassenger() {
        PassengerService passengerService = new PassengerService();
        Passenger yosr = new Passenger("Yosr", "Labidi", new UUID(11,2323));
        assertEquals(Optional.empty(), passengerService.getPassenger(new UUID(11,2323)));
        passengerService.savePassenger(yosr);
        assertEquals(yosr, passengerService.getPassenger(yosr.getId()).get());


    }

    @Test
    public void testDeletePassenger() {
        PassengerService passengerService = new PassengerService();
        Passenger mehdi = new Passenger("Mehdi", "Gharam", new UUID(5,3));
        passengerService.savePassenger(mehdi);
        assertEquals(1, passengerService.getAllPassengers().size());
        passengerService.deletePassenger(mehdi.getId());
        assertEquals(0, passengerService.getAllPassengers().size());

    }

    @Test
    public void testCheckExistingPassenger() {
        PassengerService passengerService = new PassengerService();
        Passenger amine = new Passenger("Amine", "Haddad",  new UUID(4,3));
        Passenger eslam = new Passenger("Eslam", "Nasrallah",  new UUID(8,3));

        assertEquals(Optional.empty(), passengerService.checkExistingPassenger(amine));
        passengerService.savePassenger(amine);
        assertEquals(amine, passengerService.checkExistingPassenger(amine).get());
        assertEquals(Optional.empty(), passengerService.checkExistingPassenger(eslam));
        passengerService.savePassenger(eslam);
        assertEquals(eslam, passengerService.checkExistingPassenger(eslam).get());


    }

    @Test
    public void testGetAllPassengers() {
        PassengerService passengerService = new PassengerService();
        assertEquals(0, passengerService.getAllPassengers().size());
        Passenger alaa = new Passenger("Alaa", "Demmer", new UUID(2,2));
        Passenger anna = new Passenger("Anna", "Cramling", new UUID(4,9));
        passengerService.savePassenger(alaa);
        passengerService.savePassenger(anna);

        assertEquals(2, passengerService.getAllPassengers().size());



    }
}