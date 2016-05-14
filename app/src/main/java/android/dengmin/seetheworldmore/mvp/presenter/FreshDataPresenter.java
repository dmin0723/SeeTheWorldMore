package android.dengmin.seetheworldmore.mvp.presenter;

import android.dengmin.seetheworldmore.mvp.interf.NewsModel;
import android.dengmin.seetheworldmore.mvp.interf.NewsPresenter;
import android.dengmin.seetheworldmore.mvp.interf.NewsView;
import android.dengmin.seetheworldmore.mvp.interf.OnLoadDataListener;
import android.dengmin.seetheworldmore.mvp.model.FreshDetailJson;
import android.dengmin.seetheworldmore.mvp.model.FreshJson;
import android.dengmin.seetheworldmore.mvp.model.FreshModel;
import android.dengmin.seetheworldmore.mvp.model.FreshPost;
import android.dengmin.seetheworldmore.ui.BaseActivity;

/**
 * Created by dmin on 20165/12.
 * helps to present fresh news list
 */
public class FreshDataPresenter implements NewsPresenter,OnLoadDataListener {

    private NewsView<FreshJson> mNewsView;
    private NewsModel<FreshPost,FreshDetailJson> mNewsModel;

    public FreshDataPresenter(NewsView<FreshJson> newsView, BaseActivity activity){
        this.mNewsView = newsView;
        mNewsModel = new FreshModel(activity);
    }

    @Override
    public void loadNews() {
        mNewsView.showProgress();
        mNewsModel.getNews(FreshModel.TYPE_FRESH,this);
    }

    @Override
    public void loadBefore() {
        mNewsView.showProgress();
        mNewsModel.getNews(FreshModel.TYPE_CONTINUOUS,this);
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
