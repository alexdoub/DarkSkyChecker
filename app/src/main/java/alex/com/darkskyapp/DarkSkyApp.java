package alex.com.darkskyapp;

import android.app.Application;

import com.facebook.stetho.Stetho;

import alex.com.darkskyapp.dagger.AppComponent;
import alex.com.darkskyapp.dagger.DaggerAppComponent;
import timber.log.Timber;

/**
 * Created by Alex on 11/11/2017.
 */

public class DarkSkyApp extends Application {

    private static AppComponent _AppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
        Stetho.initializeWithDefaults(this);

        _AppComponent = DaggerAppComponent.builder().build();
    }

    public static AppComponent getAppComponent() {
        return _AppComponent;
    }
}
