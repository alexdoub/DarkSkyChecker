package alex.com.darkskyapp.components.app.data;

import android.content.SharedPreferences;
import android.location.Location;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import alex.com.darkskyapp.config.Constants;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import timber.log.Timber;

/**
 * Created by Alex on 11/12/2017.
 */

public class UserDataManager {

    private static String KEY_LAST_LOCATION_NAME = "location";
    private static String KEY_LAST_LOCATION_LATITUDE = "latitude";
    private static String KEY_LAST_LOCATION_LONGITUDE = "longitude";

    private ArrayList<Location> simulatedGPSLocations = new ArrayList<>();
    private BehaviorSubject<Location> gpsLocationSubject = BehaviorSubject.create();
    private SharedPreferences prefs;

    public UserDataManager(SharedPreferences prefs) {

        this.prefs = prefs;

        //Listen for GPS updates
        gpsLocationSubject.subscribe(this::saveLastLocation);

        //Create simulated GPS simulatedGPSLocations
        simulatedGPSLocations.add(Constants.LosAngeles());
        simulatedGPSLocations.add(Constants.NewYork());
        simulatedGPSLocations.add(Constants.Alaska());
        simulatedGPSLocations.add(Constants.Taiwan());
        simulatedGPSLocations.add(Constants.Miami());
    }

    public Location getLastOrDefaultLocation() {

        String lastLocationName = prefs.getString(KEY_LAST_LOCATION_NAME, null);
        float lastLocationLatitude = prefs.getFloat(KEY_LAST_LOCATION_LATITUDE, 0f);
        float lastLocationLongitude = prefs.getFloat(KEY_LAST_LOCATION_LONGITUDE, 0f);

        if (lastLocationName == null || lastLocationName.length() == 0) {
            Timber.i("getLastOrDefaultLocation loading default");
            return Constants.Alaska();
        } else {
            Timber.i("getLastOrDefaultLocation loading stored: " + lastLocationName);
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
        Timber.d("Saved location: " + location.getProvider());
        editor.apply();
    }

    //Simulate GPS changing location
    public BehaviorSubject<Location> getGPSLocationObservable() {
        return gpsLocationSubject;
    }

    public void simulateGPSUpdate() {
        Location newLocation = simulatedGPSLocations.remove(0);
        simulatedGPSLocations.add(newLocation);
        Timber.e("Simulating change to location: " + newLocation.getProvider());

        gpsLocationSubject.onNext(newLocation);
    }
}
