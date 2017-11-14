package alex.com.darkskyapp.components.app.data;

import android.content.SharedPreferences;
import android.location.Location;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;

/**
 * Created by Alex on 11/12/2017.
 */

public class LocationManager {

    private static String KEY_LAST_LOCATION_NAME = "location";
    private static String KEY_LAST_LOCATION_LATITUDE = "latitude";
    private static String KEY_LAST_LOCATION_LONGITUDE = "longitude";

    //Simulated Locations
    private static Location LosAngeles = From("Los Angeles", 37.130372, -113.628868);
    private static Location NewYork = From("New York", 40.71448, -74.00598);
    private static Location Taiwan = From("Taiwan", 25.059066, 121.533335);
    private static Location Miami = From("Miami", 25.754558, -80.196714);
    private static Location NewOrleans = From("New Orleans", 29.934483, -90.086655);
    private static Location Singapore = From("Singapore", 1.333175, 103.874877);
    private static Location Alaska = From("Alaska", 66.160507, -153.369141);


    private ArrayList<Location> simulatedGPSLocations = new ArrayList<>();
    private PublishSubject<Location> gpsLocationSubject = PublishSubject.create();
    private SharedPreferences prefs;

    public LocationManager(SharedPreferences prefs) {

        this.prefs = prefs;

        //Listen for GPS updates
        gpsLocationSubject.subscribe(this::saveLastLocation);

        //Create simulated GPS simulatedGPSLocations
        simulatedGPSLocations.add(LosAngeles);
        simulatedGPSLocations.add(NewYork);
        simulatedGPSLocations.add(Taiwan);
        simulatedGPSLocations.add(Miami);
        simulatedGPSLocations.add(Singapore);
        simulatedGPSLocations.add(NewOrleans);
        simulatedGPSLocations.add(Alaska);
    }

    private static Location From(String name, double lat, double lng) {
        Location location = new Location(name);
        location.setLatitude(lat);
        location.setLongitude(lng);
        return location;
    }

    public Location getLastSavedLocationOrDefault() {

        String lastLocationName = prefs.getString(KEY_LAST_LOCATION_NAME, null);
        float lastLocationLatitude = prefs.getFloat(KEY_LAST_LOCATION_LATITUDE, 0f);
        float lastLocationLongitude = prefs.getFloat(KEY_LAST_LOCATION_LONGITUDE, 0f);

        if (lastLocationName == null || lastLocationName.length() == 0) {
            Timber.i("getLastSavedLocationOrDefault loading default");
            return Alaska;
        } else {
            Timber.i("getLastSavedLocationOrDefault loading stored: " + lastLocationName);
            Location storedLocation = new Location(lastLocationName);
            storedLocation.setLatitude(lastLocationLatitude);
            storedLocation.setLongitude(lastLocationLongitude);
            return storedLocation;
        }
    }

    public void saveLastLocation(Location location) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_LAST_LOCATION_NAME, location.getProvider());
        editor.putFloat(KEY_LAST_LOCATION_LATITUDE, (float) location.getLatitude());
        editor.putFloat(KEY_LAST_LOCATION_LONGITUDE, (float) location.getLongitude());
        Timber.i("Saved location: " + location.getProvider());
        editor.apply();
    }

    public PublishSubject<Location> getGPSLocationObservable() {
        return gpsLocationSubject;
    }

    //Simulating a GPS better shows off this app as you can select different locations
    //and adding real GPS hookups would be a lot more hassle
    public void simulateGPSUpdate() {
        Location newLocation = simulatedGPSLocations.remove(0);
        simulatedGPSLocations.add(newLocation);
        Timber.i("Simulating GPS update to location in 200ms: " + newLocation.getProvider());

        Observable.just(newLocation)
                .delay(200, TimeUnit.MILLISECONDS)
                .subscribe(gpsLocationSubject::onNext);
    }
}


