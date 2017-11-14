package alex.com.darkskyapp.components.app.dagger;

import javax.inject.Inject;

import alex.com.darkskyapp.components.app.data.GPSLocationManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Alex on 11/11/2017.
 */

@Module
public class GPSLocationModule {

    @Inject
    public GPSLocationModule() {
    }

    @Provides
    GPSLocationManager provideLocationManager() {
        return new GPSLocationManager();
    }
}

