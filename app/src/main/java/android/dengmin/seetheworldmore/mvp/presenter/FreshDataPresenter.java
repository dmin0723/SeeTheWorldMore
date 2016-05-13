package android.dengmin.seetheworldmore.mvp.presenter;

import android.dengmin.seetheworldmore.mvp.interf.NewsPresenter;
import android.dengmin.seetheworldmore.mvp.interf.OnLoadDataListener;
import android.dengmin.seetheworldmore.mvp.other.NewsDetail;

/**
 * Created by dmin on 20165/12.
 */
public class FreshDataPresenter implements NewsPresenter,OnLoadDataListener {



    @Override
    public void loadNews() {

    }

    @Override
    public void loadBefore() {

    }


    @Override
    public void onSuccess(NewsDetail detailNews) {

    }

    @Override
    public void onFailure(String msg, Exception e) {

    }
}
