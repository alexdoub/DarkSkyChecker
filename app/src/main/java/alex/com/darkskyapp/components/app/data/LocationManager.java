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
    private static Location Alaska() {
        Location location = new Location("Alaska");
        location.setLatitude(66.160507);
        location.setLongitude(-153.369141);
        return location;
    }

    private static Location LosAngeles() {
        Location location = new Location("Los Angeles");
        location.setLatitude(37.130372);
        location.setLongitude(-113.628868);
        return location;
    }

    private static Location NewYork() {
        Location location = new Location("New York");
        location.setLatitude(40.71448);
        location.setLongitude(-74.00598);
        return location;
    }

    private static Location Taiwan() {
        Location location = new Location("Taipei");
        location.setLatitude(25.059066);
        location.setLongitude(121.533335);
        return location;
    }

    private static Location Miami() {
        Location location = new Location("Miami");
        location.setLatitude(25.754558);
        location.setLongitude(-80.196714);
        return location;
    }

    private ArrayList<Location> simulatedGPSLocations = new ArrayList<>();
    private PublishSubject<Location> gpsLocationSubject = PublishSubject.create();
    private SharedPreferences prefs;

    public LocationManager(SharedPreferences prefs) {

        this.prefs = prefs;

        //Listen for GPS updates
        gpsLocationSubject.subscribe(this::saveLastLocation);

        //Create simulated GPS simulatedGPSLocations
        simulatedGPSLocations.add(LosAngeles());
        simulatedGPSLocations.add(NewYork());
        simulatedGPSLocations.add(Alaska());
        simulatedGPSLocations.add(Taiwan());
        simulatedGPSLocations.add(Miami());
    }

    public Location getLastSavedLocationOrDefault() {

        String lastLocationName = prefs.getString(KEY_LAST_LOCATION_NAME, null);
        float lastLocationLatitude = prefs.getFloat(KEY_LAST_LOCATION_LATITUDE, 0f);
        float lastLocationLongitude = prefs.getFloat(KEY_LAST_LOCATION_LONGITUDE, 0f);

        if (lastLocationName == null || lastLocationName.length() == 0) {
            Timber.i("getLastSavedLocationOrDefault loading default");
            return Alaska();
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

    public void simulateGPSUpdate() {
        Location newLocation = simulatedGPSLocations.remove(0);
        simulatedGPSLocations.add(newLocation);
        Timber.i("Simulating GPS update to location in 200ms: " + newLocation.getProvider());

        Observable.just(newLocation)
                .delay(200, TimeUnit.MILLISECONDS)
                .subscribe(gpsLocationSubject::onNext);
    }
}


