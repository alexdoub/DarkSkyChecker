package alex.com.darkskyapp.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alex on 11/11/2017.
 */

public class SchedulerUtils {

    private static Executor backgroundExecutor = Executors.newCachedThreadPool();
    private static Scheduler BACKGROUND_SCHEDULERS = Schedulers.from(backgroundExecutor);

    private static Executor internetExecutor = Executors.newCachedThreadPool();
    private static Scheduler INTERNET_SCHEDULERS = Schedulers.from(internetExecutor);

    public static Scheduler background() {
        return BACKGROUND_SCHEDULERS;
    }

    public static Scheduler io() {
        return Schedulers.io();
    }

    public static Scheduler compute() {
        return Schedulers.computation();
    }

    public static Scheduler main() {
        return AndroidSchedulers.mainThread();
    }

    public static Scheduler internet() {
        return INTERNET_SCHEDULERS;
    }
}
