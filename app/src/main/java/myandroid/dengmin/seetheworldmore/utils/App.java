package myandroid.dengmin.seetheworldmore.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import myandroid.dengmin.seetheworldmore.MyApplication;
import android.net.Uri;

import java.io.File;

/**
 * Created by dmin on 2016/5/11.
 */
public class App {

    public static String getVersionName(){
        try{
            PackageManager packgeManager = MyApplication.context.getPackageManager();
            PackageInfo info = packgeManager.getPackageInfo(MyApplication.context.getPackageName(),0);
            String version = info.versionName;//
            return "版本" + version;//MyApplication.context.getString(R.string.version) + version ng;
        } catch (Exception e) {
            e.printStackTrace();
            return "无法识别版本号";
        }
    }

    public static void openAppInfo(Context context){
        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("package:" + context.getApplicationContext().getPackageName()));
        context.startActivity(intent);
    }

    public static boolean clearCache(){
        File cacheDir = MyApplication.context.getCacheDir();
        for(File file : cacheDir.listFiles()){
            if(!file.delete()){
                return false;
            }
        }
        return true;
    }
}
