package alex.com.darkskyapp.components.forecast.core;

import android.content.Context;
import android.content.Intent;

import alex.com.darkskyapp.components.forecast.ForecastDetailActivity;
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

        //Hook up listeners for button clicks
        view.refreshForecastClicks().subscribe(obj -> {
            getForecast();
        });
        view.refreshGPSClicks().subscribe(obj -> {
            refreshGPS();
        });
        view.detailsClicks().subscribe(obj -> {
            goToForecastDetailsActivity();
        });

        //Hook up listener for GPS location updating
        model.getLocationObservable().subscribe(location ->
            view.bindLocation(location)
        );

        model.refreshLocationFromGPS();
    }

    public void goToForecastDetailsActivity() {

        Context c = view.view().getContext();
        Intent in = new Intent(c, ForecastDetailActivity.class);
        c.startActivity(in);

    }

    private void getForecast() {
        model.getForecast()
                .observeOn(SchedulerUtils.main())
                .subscribe(view::bindForecast, ViewUtils::handleThrowable);
    }

    private void refreshGPS() {
        model.refreshLocationFromGPS();
    }

}
