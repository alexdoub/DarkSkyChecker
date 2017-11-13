package alex.com.darkskyapp.components.app.dagger;

import android.content.Context;

import javax.inject.Inject;

import alex.com.darkskyapp.components.app.UserDataManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Alex on 11/11/2017.
 */

@Module
public class UserDataModule {

    @Inject
    public UserDataModule() {
    }

    @Provides
    UserDataManager provideUserDataManager() {
        return new UserDataManager();
    }
}

