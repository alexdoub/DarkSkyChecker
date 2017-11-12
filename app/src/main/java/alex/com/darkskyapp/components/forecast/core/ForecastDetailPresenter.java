package alex.com.darkskyapp.components.forecast.core;

import alex.com.darkskyapp.api.model.Forecast;
import alex.com.darkskyapp.utils.SchedulerUtils;
import alex.com.darkskyapp.utils.ViewUtils;

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
