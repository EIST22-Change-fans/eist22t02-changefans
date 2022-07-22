package de.changefans.service;


import de.changefans.model.POI;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class POIService {
    private final String POI_API_KEY = "108d776c4cbf492d8d8a54798fdd4b6d";
    /**
     * This method takes in a longitude and a latitude as doubles
     * It sends HTTP Get request based on these parameters to an external API and parses
     * the json response into the needed attributes to create a POI Class. It then adds each
     * created POI to a POI list until there are no more POIs to add and returns the
     * POI list.
     */
    public List<POI> getPOI(double longitude, double latitude) throws IOException {
        final String uri =
                "https://api.geoapify.com/v2/places?categories=tourism.sights,entertainment.culture&bias=proximity:" + Double.toString(longitude) + ","
                        + Double.toString(latitude) + "&limit=20&apikey=" + POI_API_KEY;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        URL url = new URL("https://test.api.amadeus.com/v1/reference-data/locations/pois?latitude=41.397158&longitude=2.160873&radius=1&page%5Blimit%5D=10&page%5Boffset%5D=0");


        assert result != null;
        JSONObject obj = new JSONObject(result);

        JSONArray arr = obj.getJSONArray("features");
        List<POI> poi = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++) {
            String typeFeature = arr.getJSONObject(i).getString("type");
            if (!typeFeature.equals("Feature")) {
                continue;
            }
            JSONObject pr = arr.getJSONObject(i).getJSONObject("properties");
            String name = pr.getString("name");
            String location = pr.getString("formatted");
            JSONArray categories = pr.getJSONArray("categories");
            String type = categories.getJSONObject(0).toString();
            String openingHours = pr.getString("opening_hours");

            POI poii = new POI(name, type, location, openingHours);
            poi.add(poii);


        }
        return new ArrayList<>();
    }

}
