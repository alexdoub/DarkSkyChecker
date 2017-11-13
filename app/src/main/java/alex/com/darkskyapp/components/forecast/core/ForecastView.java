package alex.com.darkskyapp.components.forecast.core;

import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import javax.inject.Inject;

import alex.com.darkskyapp.R;
import alex.com.darkskyapp.components.app.api.model.Forecast;
import alex.com.darkskyapp.components.forecast.ForecastActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Created by Alex on 11/11/2017.
 */

public class ForecastView {


    @BindView(R.id.title_tv) TextView titleTv;
    @BindView(R.id.location_tv) TextView locationTv;

    @BindView(R.id.container) LinearLayout container;
    @BindView(R.id.timezone_tv) TextView timezoneTv;
    @BindView(R.id.weather_icon) ImageView iconIv;

    @BindView(R.id.forecast_details) Button forecastDetails;
    @BindView(R.id.refresh_forecast) Button refreshForecast;
    @BindView(R.id.refresh_gps) Button refreshGPSLocation;

    View view;
    //Context context;

    @Inject
    public ForecastView(ForecastActivity context) {
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_forecast, parent, true);
        ButterKnife.bind(this, view);
    }

    void bindForecast(Forecast forecast) {

        container.setVisibility(forecast != null ? View.VISIBLE : View.GONE);

        if (forecast != null) {
            timezoneTv.setText(view.getContext().getString(R.string.timezone_for, forecast.timezone));

            int resourceId = view.getContext().getResources().getIdentifier(forecast.currently.getIconStr(), "drawable", view.getContext().getPackageName());
            if (resourceId == 0) {
                Timber.e("WARNING - No icon set for: " + forecast.currently.getIconStr());
            }
            iconIv.setImageResource(resourceId);
        }
    }

    void bindLocation(Location location) {
        titleTv.setText(view.getContext().getString(R.string.your_location, location.getLatitude(), location.getLongitude()));
    }

    public View view() {
        return view;
    }

    public Observable<Object> detailsClicks() {
        return RxView.clicks(forecastDetails);
    }

    public Observable<Object> refreshForecastClicks() {
        return RxView.clicks(refreshForecast);
    }

    public Observable<Object> refreshGPSClicks() {
        return RxView.clicks(refreshGPSLocation);
    }

}
