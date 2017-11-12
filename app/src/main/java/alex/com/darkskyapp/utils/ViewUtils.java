package alex.com.darkskyapp.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import timber.log.Timber;

/**
 * Created by Alex on 11/11/2017.
 */

public class ViewUtils {

//    public static String GetString(int id) {
//        return GDAXManagerApp.getInstance().getString(id);
//    }

    public static void ShowToast(Context context, String message) {
        ShowToast(context, message, true);
    }

    public static void ShowToast(Context context, String message, boolean isShort) {
        Toast.makeText(context, message, isShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG).show();
    }

    public static void handleThrowable(Throwable throwable) {
        Timber.e(throwable, throwable.toString());
    }

    public static void showSnackbar(View view, String message, int length) {
        Snackbar.make(view, message, length).setAction("Action", null).show();
    }


}
