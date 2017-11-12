package alex.com.darkskyapp.screens.weather.dagger;

import alex.com.darkskyapp.dagger.AppComponent;
import alex.com.darkskyapp.screens.weather.WeatherActivity;
import dagger.Component;

/**
 * Created by Alex on 11/11/2017.
 */

@WeatherScope
@Component(dependencies = {AppComponent.class} , modules = {WeatherModule.class})
public interface WeatherComponent {

    void inject (WeatherActivity weatherActivity);
}
