package alex.com.darkskyapp.api;

import java.util.List;

import alex.com.darkskyapp.model.Forecast;
import alex.com.darkskyapp.utils.SchedulerUtils;
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
