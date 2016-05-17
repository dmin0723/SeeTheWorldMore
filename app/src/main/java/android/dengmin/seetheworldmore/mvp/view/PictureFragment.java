package android.dengmin.seetheworldmore.mvp.view;

import android.dengmin.seetheworldmore.mvp.interf.OnLoadDataListener;
import android.dengmin.seetheworldmore.mvp.interf.UpdateReceiver;
import android.dengmin.seetheworldmore.mvp.model.Image;
import android.dengmin.seetheworldmore.mvp.other.PictureAdapter;
import android.dengmin.seetheworldmore.mvp.presenter.FetchService;
import android.dengmin.seetheworldmore.net.API;
import android.dengmin.seetheworldmore.net.DB;
import android.dengmin.seetheworldmore.net.Net;
import android.dengmin.seetheworldmore.ui.BaseActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.zhy.http.okhttp.callback.StringCallback;

import io.realm.RealmResults;
import okhttp3.Call;

/**
 * Created by dmin on 2016/5/15.
 * Gank and DB beauty fragment
 */
public class PictureFragment extends RecyclerFragment implements OnLoadDataListener {

    public static final int TYPE_GANK = 0;
    public static final int TYPE_DB_BREAST = 1;
    public static final int TYPE_DB_BUTT = 2;
    public static final int TYPE_DB_SILK = 3;
    public static final int TYPE_DB_LEG = 4;
    public static final int TYPE_DB_RANK = 5;

    private static final int LOAD_COUNT_LARGE = 15;
    private static final int REQUEST_VIEW = 1;
    private static int LOAD_COUNT = 8;
    private static int PRELOAD_COUNT = 10;

    private String url;
    private int page = 1;
    private StaggeredGridLayoutManager layoutManger;
    private PictureAdapter adapter;
    private RealmResults<Image> images;
    private long GET_DURATION = 3000;
    private UpdateReceiver loadReceiver;
    private LocalBroadcastManager localBrocastManager;
    private BaseActivity context;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if(lastPosition > layoutManger.getItemCount() - PRELOAD_COUNT){
            PRELOAD_COUNT++;
            fetch(false);
        }
    }

    private void fetch(boolean fresh) {
        initUrl(fresh);
        getData();
    }

    private void getData() {
        final long lastGetTime = System.currentTimeMillis();
        StringCallback callback = new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                if(System.currentTimeMillis() - lastGetTime < GET_DURATION){
                    Net.get(url,this,API.TAG_PICTURE);
                    return;
                }
                onFailure("load failed");
            }

            @Override
            public void onResponse(String response) {
                if(isAlive()){
                    FetchService.startActionFetch(getActivity(),type,response);
                }
            }
        };

        Net.get(url,callback,API.TAG_PICTURE);//细节出错
    }

    private void initUrl(boolean fresh) {
        if(fresh){
            page = 1;
            isFirst = true;
        }else{
            isFirst = false;
        }

        switch (type){
            case TYPE_DB_BREAST:
                url = API.DB_BREAST + page;
                break;
            case TYPE_DB_BUTT:
                url = API.DB_BUTT + page;
                break;
            case TYPE_DB_LEG:
                url = API.DB_lEG + page;
                break;
            case  TYPE_DB_SILK:
                url = API.DB_SILK + page;
                break;
            case TYPE_DB_RANK:
                url = API.DB_RANK + page;
                break;
            default://tepe = 0 ,代表GANK
                url = API.GANK + LOAD_COUNT + "/" + page;
                if(isFirst){
                    //if first load, wo make load count larger next time
                    //(coz user has  image to see)
                    LOAD_COUNT = LOAD_COUNT_LARGE;
                }
                break;
        }
    }

    //    public static PictureFragment newInstance(int type){
//        Bundle args = new Bundle();
//        args.putInt(Constants.TYPE,type);
//        PictureFragment fragment = new PictureFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }


    @Override
    protected void AlwaysInit() {
        super.AlwaysInit();
    }

    @Override
    protected void initData() {
       images = DB.getImages(context.mRealm,type);
        if(images.isEmpty()){
            swipeRefresh.post(new Runnable() {
                @Override
                public void run() {
                    showProgress(true);
                }
            });
            fetch(true);
            return;
        }
        adapter.addAll(images);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccess() {
        showProgress(false);
        adapter.replaceWith(images);
        page++;
    }

    @Override
    public void onFailure(String msg) {
        showProgress(false);
        fetch(false);
    }

    @Override
    public void onRefresh() {
        fetch(true);
    }
}
