package alex.com.darkskyapp.api.model;

/**
 * Created by Alex on 11/12/2017.
 */

public class Weather {

    String icon;
    public String summary;


    public String getIconStr() {
        return icon.replace("-", "_");
    }
}
