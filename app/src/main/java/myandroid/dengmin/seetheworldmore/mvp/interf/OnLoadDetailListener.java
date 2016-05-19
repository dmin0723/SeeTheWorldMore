package myandroid.dengmin.seetheworldmore.mvp.interf;

import myandroid.dengmin.seetheworldmore.mvp.other.NewsDetail;

/**
 * Created by dmin on 2016/5/13.
 * when detail loaded, this interface is called
 */
public interface OnLoadDetailListener  <T extends NewsDetail>{
        void onDetailSuccess(T detailNews);
        void onFailure(String msg,Exception e);
}
