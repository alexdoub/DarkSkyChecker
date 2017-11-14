package alex.com.darkskyapp.components.app.data;

import android.location.Location;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;

/**
 * Created by Alex on 11/12/2017.
 */

public class GPSLocationManager {

    //Simulated Locations
    static Location LosAngeles = From("Los Angeles", 37.130372, -113.628868);
    static Location NewYork = From("New York", 40.71448, -74.00598);
    static Location Taiwan = From("Taiwan", 25.059066, 121.533335);
    static Location Miami = From("Miami", 25.754558, -80.196714);
    static Location NewOrleans = From("New Orleans", 29.934483, -90.086655);
    static Location Singapore = From("Singapore", 1.333175, 103.874877);
    static Location Alaska = From("Alaska", 66.160507, -153.369141);


    private ArrayList<Location> simulatedGPSLocations = new ArrayList<>();
    private PublishSubject<Location> gpsLocationSubject = PublishSubject.create();

    public GPSLocationManager() {

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
                .take(1)
                .delay(200, TimeUnit.MILLISECONDS)
                .subscribe(gpsLocationSubject::onNext);
    }

}


