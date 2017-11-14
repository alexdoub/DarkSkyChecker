package alex.com.darkskyapp.components.forecast.core;

import android.location.Location;

import alex.com.darkskyapp.components.app.data.UserDataManager;
import alex.com.darkskyapp.components.app.api.APIClient;
import alex.com.darkskyapp.components.app.api.model.Forecast;
import alex.com.darkskyapp.utils.SchedulerUtils;
import io.reactivex.subjects.BehaviorSubject;
import timber.log.Timber;

/**
 * Created by Alex on 11/11/2017.
 */

public class ForecastModel {

    private APIClient apiClient;
    private UserDataManager userDataManager;
    private BehaviorSubject<Forecast> forecastSubject;
    private BehaviorSubject<Location> locationSubject;

    public ForecastModel(APIClient apiClient, UserDataManager userDataManager) {
        this.apiClient = apiClient;
        this.userDataManager = userDataManager;
        forecastSubject = BehaviorSubject.create();
        locationSubject = BehaviorSubject.createDefault(userDataManager.getLastOrDefaultLocation());
    }

    public void refreshLocationFromGPS() {
        userDataManager.getGPSLocationObservable()
                .take(1)
                .subscribe(newLocation -> {
                    Timber.i("ForecastModel updated selectedLocation to: " + newLocation.getProvider());
                    locationSubject.onNext(newLocation);
                });

        userDataManager.simulateGPSUpdate();
    }

    public Location getSelectedLocation() {
        return locationSubject.getValue();
    }

    void getForecastForLocation() {
        Location selectedLocation = getSelectedLocation();
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
