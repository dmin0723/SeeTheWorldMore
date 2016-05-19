package myandroid.dengmin.seetheworldmore.mvp.interf;

import myandroid.dengmin.seetheworldmore.mvp.other.Data;

/**
 * Created by dmin on 2016/5/12.
 * fragment or activity need to implement this to show news list.
 */
public interface NewsView <T extends Data>{
    void showProgress();
    void addNews(T news);
    void hidePregress();
    void loadFailed(String msg);
}