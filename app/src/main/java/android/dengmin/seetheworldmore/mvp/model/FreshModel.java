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
import android.dengmin.seetheworldmore.utils.SharedPreferencesUtil;

import com.zhy.http.okhttp.callback.Callback;

import io.realm.Realm;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by dmin on 2016/5/13.
 * deals with the fresh new's data work
 */
public class FreshModel implements NewsModel<FreshPost,FreshDetailJson> {

    //clear page record to zero and start new request
    public static final int TYPE_FRESH = 0;
    //a continuous request with increasing one page each time
    public static final int TYPE_CONTINUOUS = 1;

    private static final String TAG = "test";
    private final Realm realm;//加了final后，一定要有构造函数
    private int page;
    private long lastGetTime;
    private BaseActivity mActivity;
    public static final int GET_DURATION = 3000;

    public FreshModel(BaseActivity activity){
        mActivity = activity;
        realm = mActivity.mRealm;
    }

    @Override
    public void getNews(int type,final OnLoadDataListener listener) {
        lastGetTime = System.currentTimeMillis();
        if(type == TYPE_FRESH){
            page = 1;//如果是全新请求，就初始化page为1
        }
        getFreshNews(listener);
    }

    private void getFreshNews(final OnLoadDataListener listener) {
        Callback<FreshJson> callback = new Callback<FreshJson>(){
            @Override
            public FreshJson parseNetworkResponse(Response response) throws Exception {
                return Json.parseFreshNews(response.body().string());
            }

            @Override
            public void onError(Call call, Exception e) {
                if(System.currentTimeMillis() - lastGetTime < GET_DURATION){
                    Net.get(API.FRESH_NEWS + page,this,API.TAG_FRESH);
                }
                e.printStackTrace();
                listener.onFailure("load fresh news failed");
            }

            @Override
            public void onResponse(FreshJson response) {
                if(mActivity.isFinishing()){
                    return;
                }
                DB.saveList(realm,response.getPosts());
                page++;
                SharedPreferencesUtil.save(Constants.PAGE,page);
            }
        };
        Net.get(API.FRESH_NEWS + page,callback,mActivity);
    }

    @Override
    public void getNewsDetail(FreshPost freshPost, OnLoadDetailListener<FreshDetailJson> listener) {
        if(getDataFromDB(freshPost,listener)){
            return;
        }

        requestData(freshPost,listener);
    }

    private void requestData(final FreshPost freshPost, final OnLoadDetailListener<FreshDetailJson> listener) {
        lastGetTime = System.currentTimeMillis();
        Callback<FreshDetailJson> callback = new Callback<FreshDetailJson>() {
            @Override
            public FreshDetailJson parseNetworkResponse(Response response) throws Exception {
                return Json.parseFreshDetail(response.body().string());
            }

            @Override
            public void onError(Call call, Exception e) {
                if(System.currentTimeMillis() - lastGetTime < GET_DURATION){
                    Net.get(API.FRESH_NEWS_DETAIL + freshPost.getId(),this,API.TAG_FRESH);
                    return;
                }
                e.printStackTrace();
                listener.onFailure("load fresh detail failed",e);
            }

            @Override
            public void onResponse(FreshDetailJson response) {
                DB.save(realm,response.getPost());
                listener.onDetailSuccess(response);
            }
        };
        Net.get(API.FRESH_NEWS_DETAIL + freshPost.getId(),callback,mActivity);
    }

    private boolean getDataFromDB(FreshPost freshPost,OnLoadDetailListener listener){
        FreshDetail post = DB.getById(realm,freshPost.getId(),FreshDetail.class);
        if(post != null){
            FreshDetailJson detailNews = new FreshDetailJson();
            detailNews.setPost(post);
            return true;
        }
        return false;
    }
}
