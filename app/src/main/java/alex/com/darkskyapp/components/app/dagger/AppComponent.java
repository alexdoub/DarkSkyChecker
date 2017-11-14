package alex.com.darkskyapp.components.app.dagger;

import android.content.Context;

import alex.com.darkskyapp.components.app.api.APIClient;
import alex.com.darkskyapp.components.app.data.UserDataManager;
import dagger.Component;

/**
 * Created by Alex on 11/12/2017.
 */
@AppScope
@Component(modules = {AppModule.class, NetworkModule.class, UserDataModule.class})
public interface AppComponent {
    APIClient provideAPIClient();
    UserDataManager provideUserDataManager();
    Context provideContext();
}
