package android.dengmin.seetheworldmore;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.RefWatcher;

/**
 * Created by dmin on 2016/5/11.
 */
public class MyApplication extends Application {
    private RefWatcher refWatcher;
    public static Context context;
}
