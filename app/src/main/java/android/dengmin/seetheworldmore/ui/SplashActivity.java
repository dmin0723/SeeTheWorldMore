package android.dengmin.seetheworldmore.ui;

import android.content.Intent;
import android.dengmin.seetheworldmore.MainActivity;
import android.dengmin.seetheworldmore.R;
import android.dengmin.seetheworldmore.net.API;
import android.dengmin.seetheworldmore.net.Net;
import android.dengmin.seetheworldmore.utils.Constants;
import android.dengmin.seetheworldmore.utils.DateUtil;
import android.dengmin.seetheworldmore.utils.Imager;
import android.dengmin.seetheworldmore.utils.SharedPreferencesUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import okhttp3.Call;

/**
 * Created by dmin on 2016/5/13.
 */
public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 2500;
    private static final String SPLASH = "splash";
    private ImageView splash;
    private String today;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splash = (ImageView) findViewById(R.id.splash);
        if(SharedPreferencesUtil.getBoolean(SettingFragment.ORIGINAL_SPLASH)){
            Glide.with(this).load(R.drawable.splash).crossFade(1500).into(splash);
            startAppDelay();
            return;
        }
        initSplash();
    }

    private void initSplash() {
        today = DateUtil.parseStandardDate(new Date());
        loadImageFile();
        if(!today.equals(SharedPreferencesUtil.getString(Constants.DATE))){
            getSplash();
        }
    }

    private void getSplash() {
        if(!Net.isOnline(this)){
            return;
        }

        Net.get(API.SPLASH, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                startApp();
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String url = jsonObject.getString("img");
                    SharedPreferencesUtil.save(SPLASH,url);
                    SharedPreferencesUtil.save(Constants.DATE,today);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },API.TAG_SPLASH);
    }

    private void loadImageFile() {
        String url = SharedPreferencesUtil.get(SPLASH,"");
        if("".equals(url)){
            Glide.with(this).load(R.drawable.splash).crossFade(SPLASH_DURATION).into(splash);
        }else{
            Imager.load(url,R.anim.splash_anim,splash);
        }
        startAppDelay();
    }

    private void startAppDelay() {
        splash.postDelayed(new Runnable() {
            @Override
            public void run() {
                startApp();
            }
        },SPLASH_DURATION);
    }

    private void startApp() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.abc_grow_fade_in_from_bottom,
                R.anim.abc_shrink_fade_out_from_bottom);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(API.TAG_SPLASH);
    }
}
