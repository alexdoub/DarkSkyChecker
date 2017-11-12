package alex.com.darkskyapp.dagger;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import alex.com.darkskyapp.api.APIClient;
import alex.com.darkskyapp.api.DarkSkyAPI;
import alex.com.darkskyapp.config.Constants;
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
    @AppScope
    OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .readTimeout(10, SECONDS)
                .writeTimeout(10, SECONDS)
                .connectTimeout(10, SECONDS)
                .build();
    }

    @Provides
    @AppScope
    Retrofit provideRetrofit(OkHttpClient client) {

        Retrofit restAdapter = new Retrofit.Builder()
                .client(client)
                .baseUrl(Constants.DARKSKY_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return restAdapter;
    }


    @AppScope
    @Provides
    DarkSkyAPI provideDarkSkyService(Retrofit retrofit) {
        return retrofit.create(DarkSkyAPI.class);
    }

    @Provides
    @AppScope
    APIClient provideAPIClient(DarkSkyAPI darkSkyAPI) {
        return new APIClient(Constants.DARKSKY_API_KEY, darkSkyAPI);
    }

}

