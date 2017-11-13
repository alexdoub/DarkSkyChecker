package alex.com.darkskyapp.components.forecast.core;

import android.content.Context;
import android.location.Location;

import alex.com.darkskyapp.components.app.api.APIClient;
import alex.com.darkskyapp.components.app.api.model.Forecast;
import alex.com.darkskyapp.components.app.UserDataManager;
import alex.com.darkskyapp.config.Constants;
import io.reactivex.Observable;

/**
 * Created by Alex on 11/11/2017.
 */

public class ForecastModel {

    private APIClient apiClient;
    private UserDataManager userDataManager;
    private Location location;

    public ForecastModel(APIClient apiClient, UserDataManager userDataManager) {
        this.apiClient = apiClient;
        this.userDataManager = userDataManager;
        this.location = userDataManager.getCurrentLocation();
    }

    public void refreshLocationFromGPS() {
        userDataManager.refreshLocation();
    }

    Observable<Forecast> getForecast() {
        String lat = ""+location.getLatitude();
        String lng = ""+location.getLongitude();
        return apiClient.getForecast(lat, lng);
    }

    Observable<Location> getLocationObservable() {
        return userDataManager.getLocationObservable();
    }
}
