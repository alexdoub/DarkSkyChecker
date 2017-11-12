package alex.com.darkskyapp.api;

import alex.com.darkskyapp.api.model.Forecast;
import io.reactivex.Observable;

/**
 * Created by Alex on 11/11/2017.
 */

public class APIClient {

    String _apiKey;
    DarkSkyAPI _darkSkyAPI;

    public APIClient(String apiKey, DarkSkyAPI darkSkyAPI) {
        _apiKey = apiKey;
        _darkSkyAPI = darkSkyAPI;
    }

    public Observable<Forecast> getForecast(String lat, String lng) {
        return _darkSkyAPI.getForecast(_apiKey, lat, lng);
    }
}
