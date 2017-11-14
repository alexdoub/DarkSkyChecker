package alex.com.darkskyapp.components.forecast.core;

import alex.com.darkskyapp.utils.SchedulerUtils;

/**
 * Created by Alex on 11/11/2017.
 */

public class ForecastDetailPresenter {

    private ForecastDetailView view;
    private ForecastModel model;

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
        model.getForecastSubject()
                .observeOn(SchedulerUtils.main())
                .subscribe(view::bindForecast);
    }

    private void refreshForecast() {
        view.showLoading(true);
        model.getForecastForLocation();
    }

}
