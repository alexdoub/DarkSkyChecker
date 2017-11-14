package alex.com.darkskyapp.components.app.dagger;

import android.content.SharedPreferences;

import javax.inject.Inject;

import alex.com.darkskyapp.components.app.data.LocationManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Alex on 11/11/2017.
 */

@Module
public class LocationModule {

    @Inject
    public LocationModule() {
    }

    @Provides
    LocationManager provideLocationManager(SharedPreferences preferences) {
        return new LocationManager(preferences);
    }
}

