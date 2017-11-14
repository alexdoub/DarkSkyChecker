package alex.com.darkskyapp.components.forecast.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import alex.com.darkskyapp.R;
import alex.com.darkskyapp.components.app.api.model.Weather;
import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Alex on 11/13/2017.
 */

class WeatherViewHolder extends RecyclerView.ViewHolder {

    private View view;

    @BindView(R.id.title) TextView titleTv;
    @BindView(R.id.icon) ImageView iconIv;
    @BindView(R.id.temperature) TextView temperatureTv;
    @BindView(R.id.precipitation) TextView precipitationTv;

    WeatherViewHolder(View itemView) {
        super(itemView);
        this.view = itemView;
        ButterKnife.bind(this, view);
    }

    void bind(Weather weather, WeatherViewType type) {
        int resourceId = view.getContext().getResources().getIdentifier(weather.getIconStr(), "drawable", view.getContext().getPackageName());
        if (resourceId == 0) {
            Timber.e("WARNING - No iconIv set for: " + weather.getIconStr());
        }
        iconIv.setImageResource(resourceId);

        String titleString = "";
        String temperatureString = "";
        String precipitationString = this.view.getContext().getString(R.string.precipitation, weather.precipProbability * 100);

        long currentTimeS = System.currentTimeMillis() / 1000;
        long timeDiffHours = Math.round(((weather.time - currentTimeS) / (60.0 * 60.0)) + 0.5);
        long timeDiffDays = Math.round((timeDiffHours / 24.0) + 0.5);
        switch (type) {
            case DAILY:
                titleString = this.view.getContext().getString(R.string.time_offset_days, timeDiffDays);
                temperatureString = this.view.getContext().getString(R.string.temperature_range, weather.temperatureLow, weather.temperatureHigh);
                break;
            case HOURLY:
                titleString = this.view.getContext().getString(R.string.time_offset_hours, timeDiffHours);
                temperatureString = this.view.getContext().getString(R.string.temperature, weather.temperature);
        }

        titleTv.setText(titleString);
        temperatureTv.setText(temperatureString);
        precipitationTv.setText(precipitationString);
    }

}

