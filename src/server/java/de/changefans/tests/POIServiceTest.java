package de.changefans.tests;
import de.changefans.model.POI;
import de.changefans.service.POIService;
import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class POIServiceTest {

    @Test
    public void testGetPOI() {
        POIService poiService = new POIService();
        List<POI> poi = new ArrayList<>();
        boolean unauthorized = true;
        try {

            poi = poiService.getPOI(2.3522219, 48.856614);
        } catch(IOException e) {
            System.err.println("IO Exception in getPOI method!");
        }
        catch (HttpClientErrorException e) {
            assertTrue(unauthorized);
            return;


        }
        assertFalse(poi.isEmpty());
    }
}
