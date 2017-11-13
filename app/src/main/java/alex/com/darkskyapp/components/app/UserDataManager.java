package alex.com.darkskyapp.components.app;

import android.location.Location;

import java.util.ArrayList;

import alex.com.darkskyapp.config.Constants;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by Alex on 11/12/2017.
 */

public class UserDataManager {

    private ArrayList<Location> locations = new ArrayList<>();

    private Subject<Location> locationSubject = BehaviorSubject.create();

    public UserDataManager() {

        Location losAngeles = new Location("Los Angeles");
        losAngeles.setLatitude(37.130372);
        losAngeles.setLongitude(-113.628868);

        //public static String LOCATION_LOS_ANGELES = ", ";
        //public static String LOCATION_NEW_YORK = "40.71448, -74.00598";
        //public static String LOCATION_ALASKA = "66.160507, -153.369141";


        //Temp data
        locations.add(losAngeles);
//        locations.add(new Location((Constants.LOCATION_NEW_YORK)));
//        locations.add(new Location((Constants.LOCATION_ALASKA)));
    }

    public Observable<Location> getLocationObservable() {
        return locationSubject;
    }

    public Location getCurrentLocation() {
        return locations.get(0);
    }

    //Simulate GPS changing location
    public void refreshLocation() {
        Location newLocation = locations.remove(0);
        locations.add(newLocation); //Add to end of queue
        locationSubject.onNext(newLocation);
    }
}
