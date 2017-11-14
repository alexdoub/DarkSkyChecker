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
    private DataManager dataManager;
    private BehaviorSubject<Forecast> forecastSubject;
    private BehaviorSubject<Location> locationSubject;

    public ForecastModel(APIClient apiClient, DataManager dataManager) {
        this.apiClient = apiClient;
        this.dataManager = dataManager;
        forecastSubject = BehaviorSubject.create();
        locationSubject = BehaviorSubject.create();
    }

    //Assume the GPSLocationManager is constantly spitting out new locations (always on)
    //Capture 1 location as the selected location
    void setLocationFromDataManager() {
        locationSubject.onNext(dataManager.selectedLocationSubject.getValue());
    }

    private void getForecastForLocation() {
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
