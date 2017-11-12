package alex.com.darkskyapp.screens.weather.dagger;

import alex.com.darkskyapp.api.APIClient;
import alex.com.darkskyapp.screens.weather.WeatherActivity;
import alex.com.darkskyapp.screens.weather.core.WeatherModel;
import alex.com.darkskyapp.screens.weather.core.WeatherPresenter;
import alex.com.darkskyapp.screens.weather.core.WeatherView;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Alex on 11/11/2017.
 */

@Module
public class WeatherModule {

    WeatherActivity weatherContext;

    public WeatherModule(WeatherActivity context) {
        this.weatherContext = context;
    }


    @WeatherScope
    @Provides
    WeatherView provideView() {
        return new WeatherView(weatherContext);
    }

    @WeatherScope
    @Provides
    WeatherPresenter providePresenter(WeatherView view, WeatherModel model) {
        return new WeatherPresenter(model, view);
    }


    @WeatherScope
    @Provides
    WeatherActivity provideContext() {
        return weatherContext;
    }

    @WeatherScope
    @Provides
    WeatherModel provideModel(APIClient apiClient) {
        return new WeatherModel(weatherContext, apiClient);
    }


}
