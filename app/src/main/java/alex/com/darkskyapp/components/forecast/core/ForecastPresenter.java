package alex.com.darkskyapp.components.forecast.core;

import android.content.Context;
import android.content.Intent;

import alex.com.darkskyapp.components.forecast.activity.ForecastDetailActivity;
import alex.com.darkskyapp.utils.SchedulerUtils;

/**
 * Created by Alex on 11/11/2017.
 */

public class ForecastPresenter {

    private ForecastView view;
    private ForecastModel model;

    public ForecastPresenter(ForecastModel model, ForecastView view) {
        this.model = model;
        this.view = view;
    }

    public void onCreate() {

        //Button listeners
        view.refreshForecastClicks().subscribe(obj -> {
            refreshForecast();
        });
        view.changeLocationClicks().subscribe(obj -> {
            refreshLocationFromGPS();
        });
        view.detailsClicks().subscribe(obj -> {
            goToForecastDetailsActivity();
        });

        //Model->View listeners
        model.getLocationSubject()
                .observeOn(SchedulerUtils.main())
                .subscribe(view::bindLocation);
        model.getForecastSubject()
                .observeOn(SchedulerUtils.main())
                .subscribe(view::bindForecast);
    }

    public void onStart() {
        refreshForecast();
    }

    private void goToForecastDetailsActivity() {
        Context context = view.view().getContext();
        Intent in = new Intent(context, ForecastDetailActivity.class);
        context.startActivity(in);
    }

    private void refreshForecast() {
        view.showLoading();
        model.getForecastForLocation();
    }

    private void refreshLocationFromGPS() {
        model.refreshLocationFromGPS();
    }

}
