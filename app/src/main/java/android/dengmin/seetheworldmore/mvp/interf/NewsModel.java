package android.dengmin.seetheworldmore.mvp.interf;

import android.dengmin.seetheworldmore.mvp.other.NewsDetail;
import android.dengmin.seetheworldmore.mvp.other.NewsItem;

/**
 * Created by dmin on 2016/5/13.
 * deals with the data work
 */
public interface NewsModel<T extends NewsItem,D extends NewsDetail> {
    void getNews(int type,OnLoadDataListener listener);
    void getNewsDetail(T newsItem,OnLoadDataListener<D> listener);
}
