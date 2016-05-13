package android.dengmin.seetheworldmore.mvp.interf;

import android.dengmin.seetheworldmore.mvp.other.NewsDetail;

/**
 * Created by dmin on 2016/5/13.
 * fragment or activity need to implement this to show news detail
 */
public interface NewsDetailView <T extends NewsDetail>{
    void showProgress();
    void showDrtail(T detailNews);
    void hideProgress();
    void showLoadFailed(String msg);
}
