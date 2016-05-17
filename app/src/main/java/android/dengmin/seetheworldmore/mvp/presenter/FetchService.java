package android.dengmin.seetheworldmore.mvp.presenter;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.dengmin.seetheworldmore.utils.Constants;

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

    public static final String ACTION_FETCH = "common_fetch";

    public FetchService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    public static void startActionFetch(Context context,int type,String response){
        Intent intent = new Intent(context,FetchService.class);
        intent.setAction(ACTION_FETCH);
        intent.putExtra(Constants.TYPE,type);
        intent.putExtra(Constants.DATE,response);
        context.startService(intent);
    }
}
