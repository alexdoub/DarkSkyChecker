package alex.com.darkskyapp.components.forecast.core;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import javax.inject.Inject;

import alex.com.darkskyapp.R;
import alex.com.darkskyapp.components.app.api.model.Forecast;
import alex.com.darkskyapp.components.forecast.activity.ForecastDetailActivity;
import alex.com.darkskyapp.components.forecast.list.ForecastSegmentAdapter;
import alex.com.darkskyapp.components.forecast.list.WeatherViewType;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * Created by Alex on 11/11/2017.
 */

public class ForecastDetailView {

    @BindView(R.id.location_coordinates_tv) TextView coordinatesTv;

    @BindView(R.id.location_container) LinearLayout locationContainer;
    @BindView(R.id.daily_container) LinearLayout dailyContainer;
    @BindView(R.id.hourly_container) LinearLayout hourlyContainer;
    @BindView(R.id.loading) ProgressBar loadingView;

    @BindView(R.id.daily_forecast_recycler_view) RecyclerView dailyRecyclerView;
    @BindView(R.id.hourly_forecast_recycler_view) RecyclerView hourlyRecyclerView;
    @BindView(R.id.refresh_forecast) TextView refreshForecastBtn;

    private View view;

    private ForecastSegmentAdapter dailyAdapter = new ForecastSegmentAdapter(WeatherViewType.DAILY);
    private ForecastSegmentAdapter hourlyAdapter = new ForecastSegmentAdapter(WeatherViewType.HOURLY);

    @Inject
    public ForecastDetailView(ForecastDetailActivity context) {
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_forecast_detail, parent, true);
        ButterKnife.bind(this, view);
        context.getSupportActionBar().setTitle(R.string.activity_forecast_details);

        dailyRecyclerView.setAdapter(dailyAdapter);
        hourlyRecyclerView.setAdapter(hourlyAdapter);
    }

    void bindForecast(Forecast forecast) {
        showLoading(false);

        dailyAdapter.setWeatherItems(forecast.daily.data);
        hourlyAdapter.setWeatherItems(forecast.hourly.data);
        coordinatesTv.setText(view.getContext().getString(R.string.location_coordinates, forecast.latitude, forecast.longitude));
    }

    void showLoading(boolean loading) {
        locationContainer.setVisibility(loading ? View.GONE : View.VISIBLE);
        dailyContainer.setVisibility(loading ? View.GONE : View.VISIBLE);
        hourlyContainer.setVisibility(loading ? View.GONE : View.VISIBLE);
        loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    public View view() {
        return view;
    }

    Observable<Object> refreshForecastClicks() {
        return RxView.clicks(refreshForecastBtn);
    }


}
