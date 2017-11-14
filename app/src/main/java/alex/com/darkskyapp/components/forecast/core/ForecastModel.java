package alex.com.darkskyapp.components.forecast.core;

import android.location.Location;

import alex.com.darkskyapp.components.app.api.APIClient;
import alex.com.darkskyapp.components.app.api.model.Forecast;
import alex.com.darkskyapp.components.app.data.LocationManager;
import alex.com.darkskyapp.utils.SchedulerUtils;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Alex on 11/11/2017.
 */

public class ForecastModel {

    private APIClient apiClient;
    private LocationManager locationManager;
    private BehaviorSubject<Forecast> forecastSubject;
    private BehaviorSubject<Location> locationSubject;

    public ForecastModel(APIClient apiClient, LocationManager locationManager) {
        this.apiClient = apiClient;
        this.locationManager = locationManager;
        forecastSubject = BehaviorSubject.create();
        locationSubject = BehaviorSubject.createDefault(locationManager.getLastSavedLocationOrDefault());
    }

    //Note, this probably doesn't belong here, as the ForecastModel shouldn't directly have information for the selected location (not to be confused with the forecasts location).
    //TODO: pull the 'selected location' component into a different part, so the ForecastModel only knows about the forecasts location, not the next location the user will select
    void refreshLocationFromGPS() {
        locationManager.getGPSLocationObservable()
                .take(1)
                .subscribe(locationSubject::onNext);

        locationManager.simulateGPSUpdate();
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
