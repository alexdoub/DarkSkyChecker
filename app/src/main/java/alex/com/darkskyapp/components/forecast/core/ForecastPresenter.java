package alex.com.darkskyapp.components.forecast.core;

import alex.com.darkskyapp.utils.SchedulerUtils;
import alex.com.darkskyapp.utils.ViewUtils;

/**
 * Created by Alex on 11/11/2017.
 */

public class ForecastPresenter {

    ForecastView view;
    ForecastModel model;

    public ForecastPresenter(ForecastModel model, ForecastView view) {
        this.model = model;
        this.view = view;
    }

    public void onCreate() {

        view.refreshClicks().subscribe(obj -> {
            refreshModel();
        });
        view.detailsClicks().subscribe(obj -> {
            model.goToForecastDetailsActivity();
        });
    }

    private void refreshModel() {
        getForecast();
    }

    private void getForecast() {
        model.provideForecastForLocation("37.130372", "-113.628868")
                .observeOn(SchedulerUtils.main())
                .subscribe(view::bind, ViewUtils::handleThrowable);
    }

}
