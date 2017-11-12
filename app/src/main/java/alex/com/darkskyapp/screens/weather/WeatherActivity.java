package alex.com.darkskyapp.screens.weather;

import android.app.Activity;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import alex.com.darkskyapp.DarkSkyApp;
import alex.com.darkskyapp.dagger.DaggerAppComponent;
import alex.com.darkskyapp.screens.weather.core.WeatherPresenter;
import alex.com.darkskyapp.screens.weather.core.WeatherView;
import alex.com.darkskyapp.screens.weather.dagger.DaggerWeatherComponent;
import alex.com.darkskyapp.screens.weather.dagger.WeatherModule;

import static android.os.PowerManager.ACQUIRE_CAUSES_WAKEUP;
import static android.os.PowerManager.FULL_WAKE_LOCK;
import static android.os.PowerManager.ON_AFTER_RELEASE;
import static android.view.WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;

/**
 * Created by Alex on 11/11/2017.
 */

public class WeatherActivity extends AppCompatActivity {

    @Inject WeatherView view;
    @Inject WeatherPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerWeatherComponent.builder()
                .appComponent(DarkSkyApp.getAppComponent())
                .weatherModule(new WeatherModule(this)).build()
                .inject(this);

        setContentView(view.view());
        presenter.onCreate();

        riseAndShine(this);
    }


    public static void riseAndShine(Activity activity) {
        activity.getWindow().addFlags(FLAG_SHOW_WHEN_LOCKED);

        PowerManager power = (PowerManager) activity.getSystemService(POWER_SERVICE);
        PowerManager.WakeLock lock = power.newWakeLock(FULL_WAKE_LOCK | ACQUIRE_CAUSES_WAKEUP | ON_AFTER_RELEASE, "wakeup!");
        lock.acquire();
        lock.release();
    }
}
