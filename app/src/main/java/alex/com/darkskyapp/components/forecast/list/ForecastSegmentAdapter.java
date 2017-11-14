package alex.com.darkskyapp.components.forecast.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import alex.com.darkskyapp.R;
import alex.com.darkskyapp.components.app.api.model.Weather;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Alex on 11/13/2017.
 */

public class ForecastSegmentAdapter extends RecyclerView.Adapter<WeatherViewHolder> {

    private final PublishSubject<Integer> itemClicks = PublishSubject.create();
    private ArrayList<Weather> weatherItems = new ArrayList<>();
    private WeatherViewType type;


    public ForecastSegmentAdapter(WeatherViewType type) {
        super();
        this.type = type;
    }

    public void setWeatherItems(List<Weather> weatherItems) {
        this.weatherItems.clear();
        this.weatherItems.addAll(weatherItems);
        notifyDataSetChanged();
    }

    public Observable<Integer> observeClicks() {
        return itemClicks;
    }


    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return new WeatherViewHolder(view, itemClicks);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        Weather weather = weatherItems.get(position);
        holder.bind(weather, type);
    }


    @Override
    public int getItemCount() {
        if (weatherItems != null && weatherItems.size() > 0) {
            return weatherItems.size();
        }
        return 0;
    }
}

