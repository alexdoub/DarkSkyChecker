package alex.com.darkskyapp.components.app.api.model;

/**
 * Created by Alex on 11/11/2017.
 */

public class Forecast {

    public String timezone;
    public double latitude;
    public double longitude;

    public Weather currently;
    public ForecastSegment daily;
    public ForecastSegment hourly;
}
