package alex.com.darkskyapp.components.forecast.dagger;

import alex.com.darkskyapp.components.app.dagger.AppComponent;
import alex.com.darkskyapp.components.forecast.ForecastActivity;
import alex.com.darkskyapp.components.forecast.ForecastDetailActivity;
import dagger.Component;

/**
 * Created by Alex on 11/11/2017.
 */

@ForecastScope
@Component(dependencies = {AppComponent.class} , modules = {ForecastModule.class})
public interface ForecastComponent {

    void inject (ForecastActivity forecastActivity);

    void inject (ForecastDetailActivity forecastActivity);
}
