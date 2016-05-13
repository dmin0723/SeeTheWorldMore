package android.dengmin.seetheworldmore.mvp.interf;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.dengmin.seetheworldmore.mvp.presenter.FetchService;

/**
 * Created by dmin on 2016/5/13.
 * Notify the pictures have been fetched.
 */
public class UpdateReceiver extends BroadcastReceiver{
    private OnLoadDataListener loadDataListener;

    public UpdateReceiver(OnLoadDataListener loadDataListener){
        this.loadDataListener = loadDataListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getBooleanExtra(FetchService.EXTRA_FETCHED_RESULT,false)){
            loadDataListener.onSuccess();
        }else{
            loadDataListener.onFailure("load no results");
        }
    }
}
