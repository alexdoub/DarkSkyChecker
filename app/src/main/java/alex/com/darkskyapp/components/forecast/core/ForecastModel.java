package alex.com.darkskyapp.components.forecast.core;

import alex.com.darkskyapp.api.APIClient;
import alex.com.darkskyapp.api.model.Forecast;
import alex.com.darkskyapp.components.forecast.ForecastActivity;
import io.reactivex.Observable;

/**
 * Created by Alex on 11/11/2017.
 */

public class ForecastModel {

    ForecastActivity context;
    APIClient apiClient;

    public ForecastModel(ForecastActivity context, APIClient apiClient) {
        this.context = context;
        this.apiClient = apiClient;
    }


    Observable<Forecast> provideForecastForLocation(String lat, String lng) {
        return apiClient.getForecast(lat, lng);
    }

    public void goToForecastDetailsActivity() {
        context.goToForecastDetailsActivity();
    }
}
