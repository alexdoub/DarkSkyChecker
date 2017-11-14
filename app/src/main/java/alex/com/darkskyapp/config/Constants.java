package alex.com.darkskyapp.config;

import android.location.Location;

/**
 * Created by Alex on 11/11/2017.
 */

public class Constants {

    public static final String DARKSKY_BASE_URL = "https://api.darksky.net/";
    public static final String DARKSKY_API_KEY = "49e44660d965ffab2b87cad61ec4c2e4";

    public static final String SHARED_PREFERENCES = "DarkSkyPreferences";


    //Simulated Locations
    public static Location Alaska() {
        Location location = new Location("Alaska");
        location.setLatitude(66.160507);
        location.setLongitude(-153.369141);
        return location;
    }

    public static Location LosAngeles() {
        Location location = new Location("Los Angeles");
        location.setLatitude(37.130372);
        location.setLongitude(-113.628868);
        return location;
    }

    public static Location NewYork() {
        Location location = new Location("New York");
        location.setLatitude(40.71448);
        location.setLongitude(-74.00598);
        return location;
    }

    public static Location Taiwan() {
        Location location = new Location("Taipei");
        location.setLatitude(25.059066);
        location.setLongitude(121.533335);
        return location;
    }

    public static Location Miami() {
        Location location = new Location("Miami");
        location.setLatitude(25.754558);
        location.setLongitude(-80.196714);
        return location;
    }

}
