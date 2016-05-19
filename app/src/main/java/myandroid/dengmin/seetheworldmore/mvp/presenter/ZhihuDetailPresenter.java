package myandroid.dengmin.seetheworldmore.mvp.presenter;

import myandroid.dengmin.seetheworldmore.mvp.interf.NewsDetailPresenter;
import myandroid.dengmin.seetheworldmore.mvp.interf.NewsDetailView;
import myandroid.dengmin.seetheworldmore.mvp.interf.NewsModel;
import myandroid.dengmin.seetheworldmore.mvp.interf.OnLoadDetailListener;
import myandroid.dengmin.seetheworldmore.mvp.model.ZhihuDetail;
import myandroid.dengmin.seetheworldmore.mvp.model.ZhihuModel;
import myandroid.dengmin.seetheworldmore.mvp.model.ZhihuStory;
import myandroid.dengmin.seetheworldmore.ui.BaseActivity;

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
