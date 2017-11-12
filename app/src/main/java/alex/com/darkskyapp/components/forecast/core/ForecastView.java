package alex.com.darkskyapp.components.forecast.core;

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
import alex.com.darkskyapp.api.model.Forecast;
import alex.com.darkskyapp.components.forecast.ForecastActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Created by Alex on 11/11/2017.
 */

public class ForecastView {


    @BindView(R.id.empty_view) TextView emptyViewTv;
    @BindView(R.id.container) LinearLayout container;

    @BindView(R.id.location_tv) TextView locationTv;
    @BindView(R.id.timezone_tv) TextView timezoneTv;
    @BindView(R.id.weather_icon) ImageView iconIv;
    @BindView(R.id.details_button) Button detailButton;
    @BindView(R.id.refresh_button) Button refreshButton;

    View view;
    //Context context;

    @Inject
    public ForecastView(ForecastActivity context) {
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_forecast, parent, true);
        ButterKnife.bind(this, view);
    }

    void bind(Forecast model) {

        emptyViewTv.setVisibility(model != null ? View.GONE : View.VISIBLE);
        container.setVisibility(model != null ? View.VISIBLE : View.GONE);

        if (model != null) {
            locationTv.setText(view.getContext().getString(R.string.data_for, model.latitude, model.longitude));
            timezoneTv.setText(view.getContext().getString(R.string.timezone_for, model.timezone));

            int resourceId = view.getContext().getResources().getIdentifier(model.currently.getIconStr(), "drawable", view.getContext().getPackageName());
            if (resourceId == 0) {
                Timber.e("WARNING - No icon set for: " + model.currently.getIconStr());
            }
            iconIv.setImageResource(resourceId);
        }
    }

    public View view() {
        return view;
    }

    public Observable<Object> detailsClicks() {
        return RxView.clicks(detailButton);
    }

    public Observable<Object> refreshClicks() {
        return RxView.clicks(refreshButton);
    }

}
