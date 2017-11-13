package alex.com.darkskyapp.components.app.api;

import alex.com.darkskyapp.components.app.api.model.Forecast;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Alex on 11/11/2017.
 */

public interface DarkSkyAPI {

    @GET("forecast/{key}/{latitude},{longitude}")
    Observable<Forecast> getForecast(@Path("key") String key, @Path("latitude") String latitude, @Path("longitude") String longitude);

}
