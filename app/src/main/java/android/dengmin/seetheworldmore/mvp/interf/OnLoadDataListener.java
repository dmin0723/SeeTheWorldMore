package android.dengmin.seetheworldmore.mvp.interf;

import android.dengmin.seetheworldmore.mvp.other.NewsDetail;

/**
 * Created by dmin on 2016/5/13.
 */

//下载是否成功的提示 使用了接口
public interface OnLoadDataListener <T extends NewsDetail>{
    void onSuccess(T detailNews);
    void onFailure(String msg,Exception e);
}
