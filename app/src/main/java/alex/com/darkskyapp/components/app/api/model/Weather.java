package alex.com.darkskyapp.components.app.api.model;

/**
 * Created by Alex on 11/12/2017.
 */

public class Weather {

    //Common
    String icon;
    public long time;
    public String summary;
    public double precipProbability;

    //Daily
    public double temperatureHigh;
    public double temperatureLow;

    //Hourly
    public double temperature;

    public String getIconStr() {
        return icon.replace("-", "_");
    }
}
