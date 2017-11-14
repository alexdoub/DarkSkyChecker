package alex.com.darkskyapp.components.forecast.core;

import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import javax.inject.Inject;

import alex.com.darkskyapp.R;
import alex.com.darkskyapp.components.app.api.model.Forecast;
import alex.com.darkskyapp.components.forecast.activity.ForecastActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Created by Alex on 11/11/2017.
 */

public class ForecastView {

    @BindView(R.id.location_tv) TextView locationTv;
    @BindView(R.id.location_coordinates_tv) TextView coordinatesTv;

    @BindView(R.id.forecast_container) LinearLayout container;
    @BindView(R.id.forecast_summary_tv) TextView forecastSummaryTv;
    @BindView(R.id.forecast_coordinates_tv) TextView forecastLocationTv;
    @BindView(R.id.timezone_tv) TextView timezoneTv;
    @BindView(R.id.weather_icon) ImageView iconIv;
    @BindView(R.id.loading) ProgressBar loadingView;

    @BindView(R.id.forecast_details) Button forecastDetailsBtn;
    @BindView(R.id.refresh_forecast) Button refreshForecastBtn;
    @BindView(R.id.change_location) Button changeLocationBtn;

    private View view;

    @Inject
    public ForecastView(ForecastActivity context) {
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_forecast, parent, true);
        ButterKnife.bind(this, view);
    }

    void bindForecast(Forecast forecast) {
        showLoading(false);

        timezoneTv.setText(view.getContext().getString(R.string.timezone_for, forecast.timezone));
        forecastSummaryTv.setText(getDisplaySummary(forecast));
        forecastLocationTv.setText(view.getContext().getString(R.string.location_coordinates, forecast.latitude, forecast.longitude));

        int resourceId = view.getContext().getResources().getIdentifier(forecast.currently.getIconStr(), "drawable", view.getContext().getPackageName());
        if (resourceId == 0) {
            Timber.e("WARNING - No icon set for: " + forecast.currently.getIconStr());
        }
        iconIv.setImageResource(resourceId);
    }

    void bindLocation(Location location) {
        locationTv.setText(view.getContext().getString(R.string.location_name, location.getProvider()));
        coordinatesTv.setText(view.getContext().getString(R.string.location_coordinates, location.getLatitude(), location.getLongitude()));
    }

    void showLoading(boolean loading) {
        container.setVisibility(loading ? View.GONE : View.VISIBLE);
        loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    public View view() {
        return view;
    }

    Observable<Object> detailsClicks() {
        return RxView.clicks(forecastDetailsBtn);
    }

    Observable<Object> refreshForecastClicks() {
        return RxView.clicks(refreshForecastBtn);
    }

    Observable<Object> changeLocationClicks() {
        return RxView.clicks(changeLocationBtn);
    }


    //Helpers
    private String getDisplaySummary(Forecast forecast) {

        StringBuilder builder = new StringBuilder("");
        if (forecast.currently != null) {
            builder.append(forecast.currently.summary);
            builder.append(". ");
        }
        builder.append(forecast.daily.summary);
        return builder.toString();
    }
}
