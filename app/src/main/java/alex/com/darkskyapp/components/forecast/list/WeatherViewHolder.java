package alex.com.darkskyapp.components.forecast.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import alex.com.darkskyapp.R;
import alex.com.darkskyapp.components.app.api.model.Weather;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;

/**
 * Created by Alex on 11/13/2017.
 */

public class WeatherViewHolder extends RecyclerView.ViewHolder {

    View view;

    @BindView(R.id.title) TextView titleTv;
    @BindView(R.id.icon) ImageView iconIv;
    @BindView(R.id.temperature) TextView temperatureTv;
    @BindView(R.id.precipitation) TextView precipitationTv;


    public WeatherViewHolder(View itemView, PublishSubject<Integer> clickSubject) {
        super(itemView);
        this.view = itemView;
        ButterKnife.bind(this, view);
        view.setOnClickListener(v -> clickSubject.onNext(getAdapterPosition()));
    }

    void bind(Weather weather, WeatherViewType type) {
        int resourceId = view.getContext().getResources().getIdentifier(weather.getIconStr(), "drawable", view.getContext().getPackageName());
        if (resourceId == 0) {
            Timber.e("WARNING - No iconIv set for: " + weather.getIconStr());
        }
        iconIv.setImageResource(resourceId);

        String titleString = "";
        String temperatureString = "";

        long currentTimeS = System.currentTimeMillis() / 1000;
        long timeDiffHours = (weather.time - currentTimeS) / (60 * 60);
        long timeDiffDays = timeDiffHours / 24;
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
        precipitationTv.setText(this.view.getContext().getString(R.string.precipitation, weather.precipProbability * 100));
    }

}

