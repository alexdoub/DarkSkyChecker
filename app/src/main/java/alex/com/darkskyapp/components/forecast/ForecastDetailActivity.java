package alex.com.darkskyapp.components.forecast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import alex.com.darkskyapp.components.app.DarkSkyApp;
import alex.com.darkskyapp.components.forecast.core.ForecastDetailPresenter;
import alex.com.darkskyapp.components.forecast.core.ForecastDetailView;
import alex.com.darkskyapp.components.forecast.dagger.DaggerForecastComponent;
import alex.com.darkskyapp.components.forecast.dagger.ForecastModule;

/**
 * Created by Alex on 11/12/2017.
 */

public class ForecastDetailActivity extends AppCompatActivity {

    @Inject ForecastDetailView view;
    @Inject ForecastDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerForecastComponent.builder()
                .appComponent(DarkSkyApp.getAppComponent())
                .forecastModule(new ForecastModule(this)).build()
                .inject(this);

        setContentView(view.view());
        presenter.onCreate();

    }

}
