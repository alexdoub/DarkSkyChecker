package alex.com.darkskyapp.components.app;

import android.app.Application;

import alex.com.darkskyapp.components.app.dagger.AppComponent;
import alex.com.darkskyapp.components.app.dagger.AppModule;
import alex.com.darkskyapp.components.app.dagger.DaggerAppComponent;
import alex.com.darkskyapp.components.app.dagger.DataModule;
import alex.com.darkskyapp.components.app.dagger.LocationModule;
import alex.com.darkskyapp.components.app.dagger.NetworkModule;
import alex.com.darkskyapp.components.forecast.dagger.DaggerForecastComponent;
import alex.com.darkskyapp.components.forecast.dagger.ForecastModule;
import alex.com.darkskyapp.components.forecast.dagger.ForecastComponent;
import timber.log.Timber;

/**
 * Created by Alex on 11/11/2017.
 */

public class DarkSkyApp extends Application {

    private static AppComponent _AppComponent;
    private static ForecastComponent _ForecastComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        _AppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .dataModule(new DataModule())
                .locationModule(new LocationModule()).build();

        Timber.plant(new Timber.DebugTree());
    }

    public static AppComponent getAppComponent() {
        return _AppComponent;
    }


    public static ForecastComponent getForecastServiceComponent() {
        if (_ForecastComponent == null) {
            _ForecastComponent = DaggerForecastComponent.builder()
                    .appComponent(getAppComponent())
                    .forecastModule(new ForecastModule()).build();
        }
        return _ForecastComponent;
    }
}
