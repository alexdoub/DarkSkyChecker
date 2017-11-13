package alex.com.darkskyapp.components.app.dagger;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import alex.com.darkskyapp.components.app.api.APIClient;
import alex.com.darkskyapp.components.app.api.DarkSkyAPI;
import alex.com.darkskyapp.config.Constants;
import alex.com.darkskyapp.utils.SchedulerUtils;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by Alex on 11/11/2017.
 */

@Module
public class NetworkModule {

    @Provides
    OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .readTimeout(10, SECONDS)
                .writeTimeout(10, SECONDS)
                .connectTimeout(10, SECONDS)
                .build();
    }

    @Provides
    Retrofit provideRetrofit(OkHttpClient client) {

        Retrofit restAdapter = new Retrofit.Builder()
                .client(client)
                .baseUrl(Constants.DARKSKY_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(SchedulerUtils.io()))
                .build();

        return restAdapter;
    }


    @Provides
    DarkSkyAPI provideDarkSkyService(Retrofit retrofit) {
        return retrofit.create(DarkSkyAPI.class);
    }

    @Provides
    APIClient provideAPIClient(DarkSkyAPI darkSkyAPI) {
        return new APIClient(Constants.DARKSKY_API_KEY, darkSkyAPI);
    }

}

