package alex.com.darkskyapp.screens.weather.core;

import android.util.Log;

import alex.com.darkskyapp.utils.SchedulerUtils;
import alex.com.darkskyapp.utils.ViewUtils;

/**
 * Created by Alex on 11/11/2017.
 */

public class WeatherPresenter {

    WeatherView view;
    WeatherModel model;


    public WeatherPresenter(WeatherModel model, WeatherView view) {
        this.model = model;
        this.view = view;
    }

    public void onCreate() {

        Log.d("enter to presenter", "oki");

        view.refreshClicks().subscribe(obj -> {
            System.out.println("Consuming click");
            refreshModel();
        });
    }

    private void refreshModel() {
        getWeather();
    }

    private void getWeather() {

        model.provideForecastForLocation("37.130372", "-113.628868")
                .subscribeOn(SchedulerUtils.main())
                .observeOn(SchedulerUtils.io())
                .subscribe(view::bind, ViewUtils::handleThrowable);
    }
}
