package alex.com.darkskyapp.components.forecast.dagger;

import alex.com.darkskyapp.api.APIClient;
import alex.com.darkskyapp.components.forecast.ForecastActivity;
import alex.com.darkskyapp.components.forecast.core.ForecastModel;
import alex.com.darkskyapp.components.forecast.core.ForecastView;
import alex.com.darkskyapp.components.forecast.core.ForecastPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Alex on 11/11/2017.
 */

@Module
public class ForecastModule {

    ForecastActivity forecastContext;

    public ForecastModule(ForecastActivity context) {
        this.forecastContext = context;
    }

    @ForecastScope
    @Provides
    ForecastView provideView() {
        return new ForecastView(forecastContext);
    }

    @ForecastScope
    @Provides
    ForecastPresenter providePresenter(ForecastView view, ForecastModel model) {
        return new ForecastPresenter(model, view);
    }


    @ForecastScope
    @Provides
    ForecastActivity provideContext() {
        return forecastContext;
    }

    @ForecastScope
    @Provides
    ForecastModel provideModel(APIClient apiClient) {
        return new ForecastModel(forecastContext, apiClient);
    }


}
