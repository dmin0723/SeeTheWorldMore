package android.dengmin.seetheworldmore.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.dengmin.seetheworldmore.MyApplication;
import android.preference.PreferenceManager;

/**
 * Created by dmin on 2016/5/12.
 * Helps with shared preference.
 * SharedPreferences它是一个轻量级应用程序内部轻量级的存储方案,
 * 特别适合用于保存软件配置参数
 */
public class SharedPreferencesUtil {
    private static Context context = MyApplication.context;
    private static SharedPreferences sp
            = PreferenceManager.getDefaultSharedPreferences(context);
    private static SharedPreferences.Editor editor = sp.edit();

    public static void save(String key,String value){
        editor.putString(key,value);
        editor.apply();
    }

    public static void save(String key,boolean value){
        editor.putBoolean(key,value);
        editor.apply();
    }

    public static void save(String key,int value){
        editor.putInt(key,value);
        editor.apply();
    }

    public static String get(String key,String defaultValue){
        return sp.getString(key,defaultValue);
    }

    public static String getString(String key){
        return sp.getString(key,"");
    }

    public static int getInt(String key){
        return sp.getInt(key,0);
    }

    public static boolean getBoolean(String key){
        return sp.getBoolean(key,false);
    }
}
