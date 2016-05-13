package android.dengmin.seetheworldmore.mvp.presenter;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by dmin on 2016/5/13.
 */
public class FetchService extends IntentService{

    public static final String EXTRA_FETCHED_RESULT = "fetched_result";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public FetchService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
