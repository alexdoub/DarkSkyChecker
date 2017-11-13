package alex.com.darkskyapp.components.forecast.core;

/**
 * Created by Alex on 11/11/2017.
 */

public class ForecastDetailPresenter {

    ForecastDetailView view;
    ForecastModel model;

    public ForecastDetailPresenter(ForecastModel model, ForecastDetailView view) {
        this.model = model;
        this.view = view;
    }

    public void onCreate() {


    }


}
