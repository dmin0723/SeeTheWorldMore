package android.dengmin.seetheworldmore.mvp.interf;

/**
 * Created by dmin on 2016/5/13.
 */
public interface NewsDetailPresenter <T extends NewsView> {
    void loadNewsDetail(T newsItem);
}
