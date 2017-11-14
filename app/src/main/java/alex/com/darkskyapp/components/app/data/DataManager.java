package alex.com.darkskyapp.components.app.data;

import android.content.SharedPreferences;
import android.location.Location;

import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;

import static alex.com.darkskyapp.components.app.data.GPSLocationManager.Alaska;

/**
 * Created by Alex on 11/12/2017.
 */

public class DataManager {

    private static String KEY_LAST_LOCATION_NAME = "location";
    private static String KEY_LAST_LOCATION_LATITUDE = "latitude";
    private static String KEY_LAST_LOCATION_LONGITUDE = "longitude";

    private SharedPreferences prefs;

    public BehaviorSubject<Location> selectedLocationSubject;

    public DataManager(SharedPreferences prefs) {
        this.prefs = prefs;
        selectedLocationSubject = BehaviorSubject.createDefault(getLastSavedLocationOrDefault());
        selectedLocationSubject.subscribe(this::saveLastSelectedLocation);
    }

    private Location getLastSavedLocationOrDefault() {

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

    private void saveLastSelectedLocation(Location location) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_LAST_LOCATION_NAME, location.getProvider());
        editor.putFloat(KEY_LAST_LOCATION_LATITUDE, (float) location.getLatitude());
        editor.putFloat(KEY_LAST_LOCATION_LONGITUDE, (float) location.getLongitude());
        Timber.i("Saved location: " + location.getProvider());
        editor.apply();
    }

    public void selectLocation(Location newLocation) {
        selectedLocationSubject.onNext(newLocation);
    }

}


