package alex.com.darkskyapp.components.forecast.core;

import alex.com.darkskyapp.utils.SchedulerUtils;

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
        //Hook up listeners for button clicks
        view.refreshForecastClicks().subscribe(obj -> {
            refreshForecast();
        });

        //Hook up listener for model
        model.getLocationSubject()
                .observeOn(SchedulerUtils.main())
                .subscribe(view::bindLocation);
        model.getForecastSubject()
                .observeOn(SchedulerUtils.main())
                .subscribe(view::bindForecast);
    }

    private void refreshForecast() {
        model.getForecastForLocation();
    }

}
