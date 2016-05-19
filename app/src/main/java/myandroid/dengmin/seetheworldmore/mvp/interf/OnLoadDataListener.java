package myandroid.dengmin.seetheworldmore.mvp.interf;

/**
 * Created by dmin on 2016/5/13.
 * when news loaded, this interface is called
 */

//下载是否成功的提示 使用了接口
public interface OnLoadDataListener {
    void onSuccess();
    void onFailure(String msg);
}
