package android.dengmin.seetheworldmore.mvp.model;

import android.dengmin.seetheworldmore.mvp.interf.NewsModel;
import android.dengmin.seetheworldmore.mvp.interf.OnLoadDataListener;
import android.dengmin.seetheworldmore.mvp.interf.OnLoadDetailListener;
import android.dengmin.seetheworldmore.net.API;
import android.dengmin.seetheworldmore.net.DB;
import android.dengmin.seetheworldmore.net.Json;
import android.dengmin.seetheworldmore.net.Net;
import android.dengmin.seetheworldmore.ui.BaseActivity;
import android.dengmin.seetheworldmore.utils.Constants;
import android.dengmin.seetheworldmore.utils.DateUtil;
import android.dengmin.seetheworldmore.utils.SharedPreferencesUtil;
import android.util.Log;

import com.zhy.http.okhttp.callback.Callback;

import java.util.Date;

import io.realm.Realm;
import io.realm.Sort;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by dmin on 2016/5/14.
 * deal with the zhihu news' data work
 */

    //interface NewsModel<T extends NewsItem,D extends NewsDetail>
    // method :getNews(int type,OnLoadDataListener listener);
    // method :getNewsDetail(T newsItem,OnLoadDetailListener<D> listener);
public class ZhihuModel implements NewsModel<ZhihuStory,ZhihuDetail>{

    public static final int GET_DURATION = 2000;
    private static final String TAG = "test";

    private BaseActivity mActivity;
    private String date;
    private long lastGetTime;
    private int type;
    private Realm realm;//不加final后，不一定要有构造函数

    public ZhihuModel(BaseActivity activity){
        mActivity = activity;
        realm = mActivity.mRealm;
    }

    @Override
    public void getNews(final int type, final OnLoadDataListener listener) {
        this.type = type;

        lastGetTime = System.currentTimeMillis();

        final Callback<ZhihuJson> callback = new Callback<ZhihuJson>() {
            @Override
            public ZhihuJson parseNetworkResponse(Response response) throws Exception {
                ZhihuJson zhihuJson = Json.parseZhihuNews(response.body().string());
                //此时zhihuJson已经使用了Gson来解析
                date = zhihuJson.getDate();
                if(type == API.TYPE_BEFORE){
                    SharedPreferencesUtil.save(Constants.DATE,date);
                }
                Thread.sleep(3000);
                return zhihuJson;
            }

            @Override
            public void onError(Call call, Exception e) {
                if(System.currentTimeMillis() - lastGetTime < GET_DURATION){
                    getData(this);
                    return;
                }
                e.printStackTrace();
                listener.onFailure("load zhihu news failed");
            }

            @Override
            public void onResponse(ZhihuJson zhihuJson) {
                if(mActivity.isFinishing()){
                    Log.i(TAG,"onResponse: isFinishing");
                    return;
                }
                saveZhihu(zhihuJson);
                listener.onSuccess();
            }
        };
        getData(callback);
    }

    private void getData(Callback callback) {
        if(type == API.TYPE_LATEST){
            Net.get(API.NEWS_LATEST,callback,mActivity);
        }else if(type == API.TYPE_BEFORE){
            date = SharedPreferencesUtil.get(Constants.DATE, DateUtil.parseStandardDate(new Date()));
            Net.get(API.NEWS_BEFORE,callback,mActivity);
        }
    }

    private void saveZhihu(final ZhihuJson zhihuJson) {
        if(zhihuJson != null){
            realm.beginTransaction();
            if(type == API.TYPE_LATEST){
                realm.where(ZhihuTop.class).findAll().clear();
            }
            realm.copyToRealmOrUpdate(zhihuJson);
            realm.commitTransaction();
            //按时间顺序 递减
            realm.where(ZhihuJson.class).findAllSorted(Constants.DATE, Sort.DESCENDING);
        }
    }

    @Override
    public void getNewsDetail(final ZhihuStory newsItem, final OnLoadDetailListener<ZhihuDetail> listener) {
        requestData(newsItem,listener);
    }

    private void requestData(final ZhihuStory newsItem, final OnLoadDetailListener<ZhihuDetail> listener) {
        lastGetTime = System.currentTimeMillis();

        Callback<ZhihuDetail> callback = new Callback<ZhihuDetail>() {
            @Override
            public ZhihuDetail parseNetworkResponse(Response response) throws Exception {
                return Json.parseZhihuDetail(response.body().string());
            }

            @Override
            public void onError(Call call, Exception e) {
                if(System.currentTimeMillis() - lastGetTime < GET_DURATION){
                    Net.get(API.BASE_URL + newsItem.getId(),this,API.TAG_ZHIHU);
                    return;
                }
                e.printStackTrace();
                listener.onFailure("laod zhihu detail failed",e);
            }

            @Override
            public void onResponse(ZhihuDetail response) {
                DB.saveORUpdate(mActivity.mRealm,response);
                listener.onDetailSuccess(response);
            }
        };
        Net.get(API.BASE_URL + newsItem.getId(),callback,mActivity);
    }
}
