package android.dengmin.seetheworldmore.mvp.interf;

import android.dengmin.seetheworldmore.mvp.other.Data;

/**
 * Created by dmin on 2016/5/12.
 */
public interface NewsView <T extends Data>{
    void showProgress();
    void addNews(T news);
    void hidePregress();
    void loadFailed(String msg);
}