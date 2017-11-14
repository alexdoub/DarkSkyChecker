package alex.com.darkskyapp.components.forecast.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import alex.com.darkskyapp.components.app.DarkSkyApp;
import alex.com.darkskyapp.components.forecast.core.ForecastModel;
import alex.com.darkskyapp.components.forecast.core.ForecastPresenter;
import alex.com.darkskyapp.components.forecast.core.ForecastView;

/**
 * Created by Alex on 11/11/2017.
 */

public class ForecastActivity extends AppCompatActivity {

    ForecastView view;
    ForecastPresenter presenter;

    @Inject ForecastModel forecastModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DarkSkyApp.getForecastServiceComponent().inject(this);

        view = new ForecastView(this);
        presenter = new ForecastPresenter(forecastModel, view);

        setContentView(view.view());
        presenter.onCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }
}
