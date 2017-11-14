package alex.com.darkskyapp.components.forecast.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import alex.com.darkskyapp.BuildConfig;
import alex.com.darkskyapp.components.app.DarkSkyApp;
import alex.com.darkskyapp.components.app.activity.BaseActivity;
import alex.com.darkskyapp.components.forecast.core.ForecastModel;
import alex.com.darkskyapp.components.forecast.core.ForecastView;
import alex.com.darkskyapp.components.forecast.core.ForecastPresenter;

import static android.os.PowerManager.ACQUIRE_CAUSES_WAKEUP;
import static android.os.PowerManager.FULL_WAKE_LOCK;
import static android.os.PowerManager.ON_AFTER_RELEASE;
import static android.view.WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;

/**
 * Created by Alex on 11/11/2017.
 */

public class ForecastActivity extends BaseActivity {

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

    public void goToForecastDetailsActivity() {
        Intent in = new Intent(this, ForecastDetailActivity.class);
        startActivity(in);
    }


}
