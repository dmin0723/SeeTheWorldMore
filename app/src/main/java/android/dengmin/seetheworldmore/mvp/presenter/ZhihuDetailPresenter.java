package android.dengmin.seetheworldmore.mvp.presenter;

import android.dengmin.seetheworldmore.mvp.interf.NewsDetailPresenter;
import android.dengmin.seetheworldmore.mvp.interf.NewsDetailView;
import android.dengmin.seetheworldmore.mvp.interf.NewsModel;
import android.dengmin.seetheworldmore.mvp.interf.OnLoadDetailListener;
import android.dengmin.seetheworldmore.mvp.model.ZhihuDetail;
import android.dengmin.seetheworldmore.mvp.model.ZhihuModel;
import android.dengmin.seetheworldmore.mvp.model.ZhihuStory;
import android.dengmin.seetheworldmore.ui.BaseActivity;

/**
 * Created by dmin on 2016/5/14.
 */
public class ZhihuDetailPresenter implements NewsDetailPresenter<ZhihuStory>,OnLoadDetailListener<ZhihuDetail> {

    private NewsModel<ZhihuStory,ZhihuDetail> newsModel;
    private NewsDetailView<ZhihuDetail> newsDetailView;

    public ZhihuDetailPresenter(NewsDetailView<ZhihuDetail> newsDetailView, BaseActivity activity){
        this.newsModel = new ZhihuModel(activity);
        this.newsDetailView = newsDetailView;
    }
    @Override
    public void loadNewsDetail(ZhihuStory zhihuStory) {
        newsDetailView.showProgress();
        newsModel.getNewsDetail(zhihuStory,this);
    }

    @Override
    public void onDetailSuccess(ZhihuDetail detailNews) {
        newsDetailView.showDetail(detailNews);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        newsDetailView.showLoadFailed(msg);
        newsDetailView.hideProgress();
    }
}
