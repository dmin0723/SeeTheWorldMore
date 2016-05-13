package android.dengmin.seetheworldmore.mvp.interf;

/**
 * Created by dmin on 2016/5/13.
 * helps to present news page
 */

//判断下载最新的还是之前的
public interface NewsPresenter {
    void loadNews();
    void loadBefore();
}
