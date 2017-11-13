package alex.com.darkskyapp.components.forecast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import alex.com.darkskyapp.components.app.DarkSkyApp;
import alex.com.darkskyapp.components.forecast.core.ForecastDetailPresenter;
import alex.com.darkskyapp.components.forecast.core.ForecastDetailView;
import alex.com.darkskyapp.components.forecast.core.ForecastModel;

/**
 * Created by Alex on 11/12/2017.
 */

public class ForecastDetailActivity extends AppCompatActivity {

    ForecastDetailView view;
    ForecastDetailPresenter presenter;

    @Inject ForecastModel forecastModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DarkSkyApp.getForecastServiceComponent().inject(this);

        view = new ForecastDetailView(this);
        presenter = new ForecastDetailPresenter(forecastModel, view);

        setContentView(view.view());
        presenter.onCreate();
    }

}
