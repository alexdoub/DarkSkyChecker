package alex.com.darkskyapp.components.app.dagger;

import alex.com.darkskyapp.components.app.api.APIClient;
import alex.com.darkskyapp.components.app.UserDataManager;
import dagger.Component;

/**
 * Created by Alex on 11/12/2017.
 */
@AppScope
@Component(modules = {NetworkModule.class, UserDataModule.class})
public interface AppComponent {
    APIClient provideAPIClient();

    UserDataManager provideUserDataManager();
}
