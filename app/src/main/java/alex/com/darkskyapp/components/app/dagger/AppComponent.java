package alex.com.darkskyapp.components.app.dagger;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import alex.com.darkskyapp.api.APIClient;
import dagger.Component;

/**
 * Created by Alex on 11/11/2017.
 */

@AppScope
@Component(modules = {NetworkModule.class})
public interface AppComponent {
    void inject(Activity activity);

    void inject(AppCompatActivity activity);

    APIClient apiClient();
}
