package alex.com.darkskyapp.components.app.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Alex on 11/11/2017.
 */

public class Forecast {

    public String timezone;
    public double latitude;
    public double longitude;

    //Current weather
    public String summary;
    public Weather currently;

    //Daily weather
    @SerializedName("daily.data") public List<Weather> daily;
    @SerializedName("daily.summary") public String dailySummary;

    //Hourly weather

    //Minutely weather

}
