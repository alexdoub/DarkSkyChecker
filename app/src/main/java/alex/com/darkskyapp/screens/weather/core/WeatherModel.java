package alex.com.darkskyapp.screens.weather.core;

import alex.com.darkskyapp.api.APIClient;
import alex.com.darkskyapp.model.Forecast;
import alex.com.darkskyapp.screens.weather.WeatherActivity;
import io.reactivex.Observable;

/**
 * Created by Alex on 11/11/2017.
 */

public class WeatherModel {

    WeatherActivity context;
    APIClient apiClient;

    public WeatherModel(WeatherActivity context, APIClient apiClient) {
        this.context = context;
        this.apiClient = apiClient;
    }


    Observable<Forecast> provideForecastForLocation(String lat, String lng) {
        return apiClient.getForecast(lat, lng);
    }
}
