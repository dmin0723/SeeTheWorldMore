package myandroid.dengmin.seetheworldmore;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;//跨平台的移动数据库引擎
import io.realm.RealmConfiguration;

/**
 * Created by dmin on 2016/5/11.
 * init Utils
 * 设置了一个全局变量context 和初始化了Realm
 */
public class MyApplication extends Application {
//    private RefWatcher refWatcher;//内存检测 暂时
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        setupRealm();
    }

    private void setupRealm() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
