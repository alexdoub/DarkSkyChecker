package alex.com.darkskyapp.screens.weather.core;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import javax.inject.Inject;

import alex.com.darkskyapp.R;
import alex.com.darkskyapp.model.Forecast;
import alex.com.darkskyapp.screens.weather.WeatherActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * Created by Alex on 11/11/2017.
 */

public class WeatherView {

    @BindView(R.id.title_tv) TextView titleTv;
    @BindView(R.id.refresh_button) Button refreshButton;

    View view;

    @Inject
    public WeatherView(WeatherActivity context) {
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_weather, parent, true);
        ButterKnife.bind(this, view);
    }

    void bind(Forecast model) {
        titleTv.setText(model.timezone + model.latitude + model.longitude);
    }

    public View view() {
        return view;
    }

    public Observable<Object> refreshClicks() {
        return RxView.clicks(refreshButton);
    }

}
