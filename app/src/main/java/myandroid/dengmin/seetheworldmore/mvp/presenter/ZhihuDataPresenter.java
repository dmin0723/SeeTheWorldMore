package myandroid.dengmin.seetheworldmore.mvp.presenter;

import myandroid.dengmin.seetheworldmore.mvp.interf.NewsModel;
import myandroid.dengmin.seetheworldmore.mvp.interf.NewsPresenter;
import myandroid.dengmin.seetheworldmore.mvp.interf.NewsView;
import myandroid.dengmin.seetheworldmore.mvp.interf.OnLoadDataListener;
import myandroid.dengmin.seetheworldmore.mvp.model.ZhihuDetail;
import myandroid.dengmin.seetheworldmore.mvp.model.ZhihuJson;
import myandroid.dengmin.seetheworldmore.mvp.model.ZhihuModel;
import myandroid.dengmin.seetheworldmore.mvp.model.ZhihuStory;
import myandroid.dengmin.seetheworldmore.net.API;
import myandroid.dengmin.seetheworldmore.ui.BaseActivity;

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
