package alex.com.darkskyapp.components.forecast.core;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import javax.inject.Inject;

import alex.com.darkskyapp.R;
import alex.com.darkskyapp.components.app.api.model.Forecast;
import alex.com.darkskyapp.components.forecast.ForecastDetailActivity;
import butterknife.ButterKnife;

/**
 * Created by Alex on 11/11/2017.
 */

public class ForecastDetailView {



    View view;
    //Context context;

    @Inject
    public ForecastDetailView(ForecastDetailActivity context) {
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_forecast, parent, true);
        ButterKnife.bind(this, view);
    }

    void bind(Forecast model) {

    }

    public View view() {
        return view;
    }

}
