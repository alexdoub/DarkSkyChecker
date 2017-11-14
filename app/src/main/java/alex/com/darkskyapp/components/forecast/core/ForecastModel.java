package alex.com.darkskyapp.components.forecast.core;

import android.location.Location;

import alex.com.darkskyapp.components.app.api.APIClient;
import alex.com.darkskyapp.components.app.api.model.Forecast;
import alex.com.darkskyapp.components.app.data.DataManager;
import alex.com.darkskyapp.components.app.data.GPSLocationManager;
import alex.com.darkskyapp.utils.SchedulerUtils;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Alex on 11/11/2017.
 */

public class ForecastModel {

    private APIClient apiClient;
    private GPSLocationManager GPSLocationManager;
//    private DataManager dataManager;
    private BehaviorSubject<Forecast> forecastSubject;
    private BehaviorSubject<Location> locationSubject;

    public ForecastModel(APIClient apiClient, GPSLocationManager GPSLocationManager, DataManager dataManager) {
        this.apiClient = apiClient;
        this.GPSLocationManager = GPSLocationManager;
//        this.dataManager = dataManager;
        forecastSubject = BehaviorSubject.create();
        locationSubject = BehaviorSubject.createDefault(dataManager.getLastSavedLocationOrDefault());
        locationSubject.subscribe(dataManager::saveLastSelectedLocation);   //Make DataManager listen for selected locations & store them
    }

    //Assume the GPSLocationManager is constantly spitting out new locations (always on)
    //Capture 1 location as the selected location
    void refreshLocationFromGPS() {
        GPSLocationManager.getGPSLocationObservable()
                .take(1)
                .subscribe(locationSubject::onNext);

        GPSLocationManager.simulateGPSUpdate();
    }

    void getForecastForLocation() {
        Location selectedLocation = locationSubject.getValue();
        String lat = "" + selectedLocation.getLatitude();
        String lng = "" + selectedLocation.getLongitude();
        apiClient.getForecast(lat, lng)
                .observeOn(SchedulerUtils.main())
                .subscribe(forecastSubject::onNext);
    }

    BehaviorSubject<Location> getLocationSubject() {
        return locationSubject;
    }

    BehaviorSubject<Forecast> getForecastSubject() {
        return forecastSubject;
    }
}
