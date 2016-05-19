package myandroid.dengmin.seetheworldmore.mvp.presenter;

import myandroid.dengmin.seetheworldmore.mvp.interf.NewsDetailPresenter;
import myandroid.dengmin.seetheworldmore.mvp.interf.NewsDetailView;
import myandroid.dengmin.seetheworldmore.mvp.interf.NewsModel;
import myandroid.dengmin.seetheworldmore.mvp.interf.OnLoadDetailListener;
import myandroid.dengmin.seetheworldmore.mvp.model.FreshDetailJson;
import myandroid.dengmin.seetheworldmore.mvp.model.FreshModel;
import myandroid.dengmin.seetheworldmore.mvp.model.FreshPost;
import myandroid.dengmin.seetheworldmore.ui.BaseActivity;

/**
 * Created by dmin on 2016/5/14.
 * helps to present fresh news detail page
 */
public class FreshDetailPresenter implements NewsDetailPresenter<FreshPost>,OnLoadDetailListener<FreshDetailJson> {

    private NewsModel<FreshPost,FreshDetailJson> mNewsModel;
    private NewsDetailView<FreshDetailJson> newsDetailView;

    public FreshDetailPresenter(NewsDetailView<FreshDetailJson> newsDetailView, BaseActivity activity){
        this.mNewsModel = new FreshModel(activity);
        this.newsDetailView = newsDetailView;
    }

    @Override
    public void loadNewsDetail(FreshPost freshPost) {
        newsDetailView.showProgress();
        mNewsModel.getNewsDetail(freshPost,this);
    }

    @Override
    public void onDetailSuccess(FreshDetailJson detailNews) {
        newsDetailView.showDetail(detailNews);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        newsDetailView.showLoadFailed(msg);
        newsDetailView.hideProgress();
    }
}
