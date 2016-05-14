package android.dengmin.seetheworldmore.mvp.presenter;

import android.dengmin.seetheworldmore.mvp.interf.NewsModel;
import android.dengmin.seetheworldmore.mvp.interf.NewsPresenter;
import android.dengmin.seetheworldmore.mvp.interf.NewsView;
import android.dengmin.seetheworldmore.mvp.interf.OnLoadDataListener;
import android.dengmin.seetheworldmore.mvp.model.ZhihuDetail;
import android.dengmin.seetheworldmore.mvp.model.ZhihuJson;
import android.dengmin.seetheworldmore.mvp.model.ZhihuModel;
import android.dengmin.seetheworldmore.mvp.model.ZhihuStory;
import android.dengmin.seetheworldmore.net.API;
import android.dengmin.seetheworldmore.ui.BaseActivity;

/**
 * Created by dmin on 2016/5/14.
 * helps to present zhihu news list
 */
public class ZhihuDataPresenter implements NewsPresenter,OnLoadDataListener {

    private NewsView<ZhihuJson> mNewsView;
    private NewsModel<ZhihuStory,ZhihuDetail> mNewsModel;

    public ZhihuDataPresenter(NewsView<ZhihuJson> newsView, BaseActivity activity){
        this.mNewsView = newsView;
        mNewsModel = new ZhihuModel(activity);
    }

    @Override
    public void loadNews() {
        mNewsView.showProgress();
        mNewsModel.getNews(API.TYPE_LATEST,this);
    }

    @Override
    public void loadBefore() {
        mNewsModel.getNews(API.TYPE_BEFORE,this);
    }

    @Override
    public void onSuccess() {
        mNewsView.addNews(null);
        mNewsView.hidePregress();
    }

    @Override
    public void onFailure(String msg) {
        mNewsView.hidePregress();
        mNewsView.loadFailed(msg);
    }
}
